/*
 * Copyright 2017 luis.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jogl.impl.scene;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jogl.api.Camera;
import org.jogl.api.Config;
import org.jogl.api.Filter;
import org.jogl.api.GlobalLight;
import org.jogl.api.Mesh;
import org.jogl.api.Object3D;
import org.jogl.api.RenderTexture;
import org.jogl.api.Scene;
import org.jogl.api.Shader;
import org.jogl.api.Texture;
import org.jogl.api.input.Keyboard;
import org.jogl.api.input.events.Mouse;
import org.jogl.impl.util.BufferUtils;
import org.jogl.impl.util.Util;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 *
 * @author luis
 */
public abstract class AbstractScene implements Scene {

    /**
     * Objects references
     */
    private final List<Scene.MeshReference> objects = new ArrayList<>();
    private final Map<Integer, Texture> textures = new HashMap<>();
    private final Map<Integer, RenderTexture> renderTextures = new HashMap<>();
    private final List<GlobalLight> lights = new ArrayList<>();
    private Map<Shader, List<MeshReference>> shaderRef = new HashMap<>();

    private Shader shader;
    private Camera camera;

    protected Mouse mouse;
    protected Keyboard keyboard;

    @Override
    public Scene init() {

        if (camera == null) {
            throw new IllegalStateException("Camera not defined, please set it to Scene");
        }

        glClearColor(0.0f, 0.0f, 0.2f, 1.0f);

        if (Config.cullFace) {
            glEnable(GL_CULL_FACE);
        }

        if (Config.showOnlyLines) {
            glPolygonMode(GL_FRONT_FACE, GL_LINE);
        }

        if (shader != null) {
            try {
                shader.compile();
            } catch (Exception ex) {
                Logger.getLogger(AbstractScene.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }

        return this;
    }

    @Override
    public Scene setMouse(Mouse m) {
        this.mouse = m;
        return this;
    }

    @Override
    public Scene setKeyboard(Keyboard m) {
        this.keyboard = m;
        return this;
    }

    @Override
    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }

    @Override
    public List<GlobalLight> getLights() {
        return null;
    }

    @Override
    public List<Filter> getFilters() {
        return null;
    }

    @Override
    public List<Object3D> getObjects() {
        return null;
    }

    @Override
    public Scene deiInit() {
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_CULL_FACE);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        return this;
    }

    @Override
    public Scene addLight(GlobalLight light) {
        lights.add(light);
        return this;
    }

    @Override
    public Scene removeLight(GlobalLight light) {
        lights.remove(light);
        return this;
    }

    @Override
    public Scene addObject(Object3D object3D) {
        if (object3D != null) {
            createObject(object3D);
        }
        return this;
    }

    protected void createObject(Object3D object3D) {

        // Used shader
        Shader usedShader = null;

        if (object3D.getMesh() == null) {
            throw new IllegalArgumentException("Object withou mesh not allowed!");
        }

        if (object3D.getMaterial() != null) {
            if (object3D.getMaterial().getTexture() != null) {
                final Texture tx = object3D.getMaterial().getTexture();
                if (tx instanceof RenderTexture) {
                    final RenderTexture rtx = (RenderTexture) tx;
                    createRenderTexture(rtx);
                } else {
                    createTexture(tx);

                }
            }

            if (object3D.getMaterial().getShader() != null) {
                usedShader = object3D.getMaterial().getShader();
            }
        }

        if (usedShader != null) {

            // Compile
            if (!shaderRef.containsKey(usedShader)) {
                try {
                    usedShader.compile();
                } catch (Exception ex) {
                    Logger.getLogger(AbstractScene.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);
                }
            }

        }

        final int meshId = createMesh();
        final Mesh mesh = object3D.getMesh();

        // Vertex array
        final Buffer vertexBuffer = BufferUtils.convert(mesh.getVertices());
        final int arrayBufferID = createBuffer(meshId, vertexBuffer);
        int elementSize = 3;

        final ArrayBuffer array = new ArrayBuffer(arrayBufferID, elementSize, (vertexBuffer.remaining() / elementSize));

        if (mesh.getIndexBuffer() != null) {
            int indexBufferID = createIndexBuffer(meshId, BufferUtils.convertIndexBuffer(mesh.getIndexBuffer()));
            array.indexBuffer = new IndexBuffer(indexBufferID, mesh.getIndexBuffer().size());
        }

        if (mesh.getNormals().isEmpty()) {
            calculateNormals(mesh);
        }

        FloatBuffer normalBuffer = BufferUtils.convert(mesh.getNormals());
        final int normalBufferID = createBuffer(meshId, vertexBuffer);
        final ArrayBuffer normals = new ArrayBuffer(normalBufferID, elementSize, (normalBuffer.remaining() / elementSize));

        // Create referece
        final MeshReference meshReference = new MeshReference(mesh, object3D, meshId, array, normals);

        final List<MeshReference> meshs;

        if (usedShader != null) {
            if (!shaderRef.containsKey(usedShader)) {
                meshs = new ArrayList<>();
                shaderRef.put(usedShader, meshs);
            } else {
                meshs = shaderRef.get(usedShader);
            }
            // Group by shader to better performance when rendering.
            meshs.add(meshReference);
        } else {// When no shader is used, we will group to use our shader
            objects.add(meshReference);
        }
    }

    protected int createBuffer(int meshId, Buffer buffer) {
        glBindVertexArray(meshId);

        int id = glGenBuffers();

        glBindBuffer(GL_ARRAY_BUFFER, id);

        Class clazz = buffer.getClass();

        if (Util.isSubClass(clazz, FloatBuffer.class)) {
            glBufferData(GL_ARRAY_BUFFER, (FloatBuffer) buffer, GL_STATIC_DRAW);

        } else if (Util.isSubClass(clazz, ByteBuffer.class)) {
            glBufferData(GL_ARRAY_BUFFER, (ByteBuffer) buffer, GL_STATIC_DRAW);

        } else if (Util.isSubClass(clazz, DoubleBuffer.class)) {
            glBufferData(GL_ARRAY_BUFFER, (DoubleBuffer) buffer, GL_STATIC_DRAW);

        } else if (Util.isSubClass(clazz, IntBuffer.class)) {
            glBufferData(GL_ARRAY_BUFFER, (IntBuffer) buffer, GL_STATIC_DRAW);

        } else if (Util.isSubClass(clazz, ShortBuffer.class)) {
            glBufferData(GL_ARRAY_BUFFER, (ShortBuffer) buffer, GL_STATIC_DRAW);
        }

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
        return id;
    }

    protected int createIndexBuffer(int meshId, Buffer buffer) {
        glBindVertexArray(meshId);

        int id = glGenBuffers();

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);

        Class clazz = buffer.getClass();

        if (Util.isSubClass(clazz, FloatBuffer.class)) {
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, (FloatBuffer) buffer, GL_STATIC_DRAW);

        } else if (Util.isSubClass(clazz, ByteBuffer.class)) {
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, (ByteBuffer) buffer, GL_STATIC_DRAW);

        } else if (Util.isSubClass(clazz, DoubleBuffer.class)) {
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, (DoubleBuffer) buffer, GL_STATIC_DRAW);

        } else if (Util.isSubClass(clazz, IntBuffer.class)) {
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, (IntBuffer) buffer, GL_STATIC_DRAW);

        } else if (Util.isSubClass(clazz, ShortBuffer.class)) {
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, (ShortBuffer) buffer, GL_STATIC_DRAW);
        }

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
        return id;
    }

    @Override
    public Scene removeObject(Object3D object3D) {

        removeFrom(object3D, objects);
        List<MeshReference> meshs;

        shaderRef.keySet().forEach((sh) -> {
            removeFrom(object3D, shaderRef.get(sh));
        });

        // TODO: crear GPU memory after remove.
        return this;
    }

    private void removeFrom(Object3D ref, List<MeshReference> list) {
        MeshReference key = null;

        for (MeshReference entry : list) {
            if (entry.object.equals(ref)) {
                key = entry;
                break;
            }
        }

        if (key != null) {
            objects.remove(key);
        } else {
            System.out.println("Can't remove object " + ref.toString() + " reference key not found, ignoring... ");
        }
    }

    @Override
    public Scene setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    @Override
    public Camera getCamera() {
        return camera;
    }

    protected void createRenderTexture(RenderTexture rtx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void createTexture(Texture tx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected int createMesh() {
        return glGenVertexArrays();
    }

    @Override
    public Scene render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        for (Map.Entry<Shader, List<MeshReference>> entry : shaderRef.entrySet()) {
            final Shader sh = entry.getKey();
            final List<MeshReference> meshs = entry.getValue();

            sh.setCamera(camera)
                    .enable()
                    .render(meshs, lights)
                    .disable();
        }

        if (getShader() != null) {
            getShader().setCamera(camera)
                    .enable()
                    .render(objects, lights)
                    .disable();
        } else if (!objects.isEmpty()) {
            Logger.getLogger(AbstractScene.class.getSimpleName()).severe("No default shader defined in scene, but there are itens without custom shader.");
        }

        return this;
    }

    @Override
    public Scene update(float secs) {
        return this;
    }

    private void calculateNormals(Mesh mesh) {
        
        System.out.println("Calculating Normals for: "+mesh.getVertices().size());
        
        final List<Vector3f> vertices = new ArrayList<>();

        if (mesh.getIndexBuffer() != null && !mesh.getIndexBuffer().isEmpty()) {
            for (Integer idx : mesh.getIndexBuffer()) {
                try {
                    vertices.add(mesh.getVertices().get(idx));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else {
            vertices.addAll(mesh.getVertices());
        }

        mesh.getNormals().clear();

        for (int i = 0; i < vertices.size(); i += 3) {

            Vector3f n1 = new Vector3f(vertices.get(i)).cross(vertices.get(i + 1));
            Vector3f n2 = new Vector3f(vertices.get(i + 1)).cross(vertices.get(i + 2));
            Vector3f n3 = new Vector3f(vertices.get(i + 2)).cross(vertices.get(i));
            Vector3f normal = n1.add(n2).add(n3).normalize();

            mesh.getNormals().add(normal);
            mesh.getNormals().add(normal);
            mesh.getNormals().add(normal);
        }
    }

}
