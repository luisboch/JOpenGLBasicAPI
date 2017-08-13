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
package org.jogl.api.screen;

import java.util.List;
import org.jogl.api.Camera;
import org.jogl.api.GlobalLight;
import org.jogl.api.Mesh;
import org.jogl.api.Object3D;
import org.jogl.api.Shader;
import org.jogl.api.TextureParameters;
import org.jogl.api.input.Keyboard;
import org.jogl.api.input.events.Mouse;

/**
 *
 * @author luis
 */
public interface Scene {
    
    Scene setMouse(Mouse m);

    Scene setKeyboard(Keyboard m);

    Scene init();

    Scene update(float secs);

    Scene draw();

    Scene deinit();
    
    
    
    /**
     * default shader
     *
     * @return self
     */
    Shader getShader();

    List<Object3D> getObjects();

    Scene addObject(Object3D object3D);

    Scene removeObject(Object3D object3D);

    Scene addLight(GlobalLight light);

    Scene removeLight(GlobalLight light);

    Scene setCamera(Camera camera);

    Camera getCamera();

    public static class MeshReference {

        public final Mesh mesh;
        public final Object3D object;
        public final int meshId;
        public final ArrayBuffer vertexArray;
        public final ArrayBuffer normalArray;
        public final TextureInfo texture;
        public final IndexBuffer indexBuffer;
//        public final 
//

        public MeshReference(Mesh mesh, Object3D object, int meshId, ArrayBuffer array, ArrayBuffer normalArray, TextureInfo texture) {
            this.mesh = mesh;
            this.object = object;
            this.meshId = meshId;
            this.vertexArray = array;
            this.normalArray = normalArray;
            this.texture = texture;
            this.indexBuffer = new IndexBuffer(-1, -1);
        }

        public MeshReference(Mesh mesh, Object3D object, int meshId, ArrayBuffer array, ArrayBuffer normalArray, TextureInfo texture, IndexBuffer indexBuffer) {
            this.mesh = mesh;
            this.object = object;
            this.meshId = meshId;
            this.vertexArray = array;
            this.normalArray = normalArray;
            this.texture = texture;
            this.indexBuffer = indexBuffer;
        }
    }

    public static class ArrayBuffer {

        public final int id;
        public final int elementSize;
        public final int elementCount;

        public ArrayBuffer(int id, int elementSize, int elementCount) {
            this.id = id;
            this.elementSize = elementSize;
            this.elementCount = elementCount;
        }

    }

    public static class IndexBuffer {

        public final int id;
        public final int elementCount;

        public IndexBuffer(int id, int elementCount) {
            this.id = id;
            this.elementCount = elementCount;
        }

        public boolean validIndexBuffer() {
            return id != -1;
        }
    }
//    

    public static class TextureInfo {

        public final int id;
        public final ArrayBuffer texCoord;
        public final TextureParameters parameters;

        public TextureInfo(int id, ArrayBuffer texCoord, TextureParameters parameters) {
            this.id = id;
            this.parameters = parameters;
            this.texCoord = texCoord;
        }

    }
}
