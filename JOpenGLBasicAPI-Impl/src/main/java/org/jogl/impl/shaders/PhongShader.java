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
package org.jogl.impl.shaders;

import java.util.List;
import java.util.Map;
import org.jogl.api.GlobalLight;
import org.jogl.api.Material;
import org.jogl.api.PhongMaterial;
import org.jogl.api.Scene;
import org.jogl.api.Shader;
import org.jogl.impl.util.FileUtil;
import org.jogl.impl.util.OpenGLUtil;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
 *
 * @author luis
 */
public class PhongShader extends AbstractShader {

    private static final String PATH;

    static {
        PATH = PhongShader.class.getClassLoader().getResource("shaders/").getPath();
    }

    private static final String VERTEX_SHADER
            = FileUtil.readFile(PATH + "phong.vert");

    private static final String FRAGMENT_SHADER
            = FileUtil.readFile(PATH + "phong.frag");

    @Override
    protected String getVertexShaderCode() {
        return VERTEX_SHADER;
    }

    @Override
    protected String getFragmetShaderCode() {
        return FRAGMENT_SHADER;
    }

    @Override
    public Shader render(List<Scene.MeshReference> objects, List<GlobalLight> light) {

        final Matrix4f world = new Matrix4f().identity();

        enable();

        // Apply camera
        OpenGLUtil.setUniform(this.programId, "uProjection", camera.getProjectionMatrix());
        OpenGLUtil.setUniform(this.programId, "uView", camera.getViewMatrix());
        OpenGLUtil.setUniform(this.programId, "uWorld", world);
        OpenGLUtil.setUniform(this.programId, "uCameraPosition", camera.getPosition());
        
        // LIGHT
        
//                .setUniform("uAmbientLight", new Vector3f(0.1f, 0.1f, 0.1f))
//                .setUniform("uDiffuseLight", new Vector3f(1.0f, 1.0f, 0.5f))
//                .setUniform("uSpecularLight", new Vector3f(1.0f, 1.0f, 1.0f))

        OpenGLUtil.setUniform(this.programId, "uLightDir", new Vector3f(-1.0f, -1.0f, -1.0f).normalize());
        OpenGLUtil.setUniform(this.programId, "uAmbientLight", new Vector3f(0.1f, 0.1f, 0.1f));
        OpenGLUtil.setUniform(this.programId, "uDiffuseLight", new Vector3f(1f, 1f, 0.5f));
        OpenGLUtil.setUniform(this.programId, "uSpecularLight", new Vector3f(1.0f, 1.0f, 1f));

        disable();
        if (objects != null) {
            objects.forEach((Scene.MeshReference ob) -> {
                glBindVertexArray(ob.meshId);

                enable();

                OpenGLUtil.setUniform(this.programId, "uPosition", ob.object.getPosition());
                OpenGLUtil.setUniform(this.programId, "uTransform", ob.object.getTransform());
                OpenGLUtil.bindBuffer(this.programId, "aVertex", ob.vertexArray, GL_FLOAT);
                OpenGLUtil.bindBuffer(this.programId, "aNormal", ob.normalArray, GL_FLOAT);
                
                if (ob.object.getMaterial() != null) {

                    final Material material = ob.object.getMaterial();

                    if (material instanceof PhongMaterial) {
                        final PhongMaterial phongMaterial = (PhongMaterial) material;
                        OpenGLUtil.setUniform(this.programId, "uAmbientMaterial", phongMaterial.getAmbientMaterial());
                        OpenGLUtil.setUniform(this.programId, "uDiffuseMaterial", phongMaterial.getDiffuseMaterial());
                        OpenGLUtil.setUniform(this.programId, "uSpecularMaterial", phongMaterial.getSpecularMaterial());
                        OpenGLUtil.setUniform(this.programId, "uSpecularPower", phongMaterial.getSpecularPower());

                    }

                    if (material.getColor() != null) {
                        OpenGLUtil.setUniform(this.programId, "uUseColor", true);
                        OpenGLUtil.setUniform(this.programId, "uColor", material.getColor());
                    } else {
                        OpenGLUtil.setUniform(this.programId, "uUseColor", false);
                    }

                    if (ob.texture != null) {
                        OpenGLUtil.setUniform(this.programId, "uUseTexture", true);
                        glActiveTexture(GL_TEXTURE0);
                        glBindTexture(GL_TEXTURE_2D, ob.texture.id);
                        OpenGLUtil.setUniform(this.programId, "uTexture", GL_TEXTURE0);
                        glBindTexture(GL_TEXTURE_2D, 0);
                        OpenGLUtil.bindBuffer(this.programId, "aTexCoord", ob.texture.texCoord, GL_FLOAT);
                    } else {
                        OpenGLUtil.setUniform(this.programId, "uUseTexture", false);
                    }

                }
                
                // Temos Index buffer?
                if(!ob.indexBuffer.validIndexBuffer()){

                    // Nao
                    glDrawArrays(GL_TRIANGLES, 0, ob.vertexArray.elementCount);
                } else {

                    // Faz o bind no indexBuffer
                    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ob.indexBuffer.id);
                    // Escreve o array.
                    glDrawElements(GL_TRIANGLES, ob.indexBuffer.elementCount, GL_UNSIGNED_INT,  0);

                    // Unbind
                    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

                }

                OpenGLUtil.unbindBuffer(this.programId, "aVertex", ob.vertexArray, GL_FLOAT);
                OpenGLUtil.unbindBuffer(this.programId, "aNormal", ob.normalArray, GL_FLOAT);
                OpenGLUtil.unbindBuffer(this.programId, "aTexCoord", null, GL_FLOAT);
                
                disable();
                glBindVertexArray(0);

            });
        }

        return this;
    }

}
