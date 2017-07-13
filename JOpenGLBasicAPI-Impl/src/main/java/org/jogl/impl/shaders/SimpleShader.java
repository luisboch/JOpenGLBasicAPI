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
import org.jogl.api.Material;
import org.jogl.api.Shader;
import org.jogl.api.screen.Scene;
import org.jogl.impl.util.FileUtil;
import org.jogl.impl.util.OpenGLUtil;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
 *
 * @author luis
 */
public class SimpleShader extends AbstractShader {

    private static final String PATH = SimpleShader.class.getResource("/shaders/").getPath();

    private static final String VERTEX_SHADER
            = FileUtil.readFile(PATH + "simpleshader.vert");

    private static final String FRAGMENT_SHADER
            = FileUtil.readFile(PATH + "simpleshader.frag");

    @Override
    protected String getVertexShaderCode() {
        return VERTEX_SHADER;
    }

    @Override
    protected String getFragmetShaderCode() {
        return FRAGMENT_SHADER;
    }

    @Override
    public Shader render(List<Scene.MeshReference> objects) {

        int texCount = 1;

        // Set camera uniforms;
        // camera projectionMatrix;
        // camera viewMatrix;
        // camera positionMatrix;
        // GlobalLight values
        // Set object local light uniforms;
        // camera LightDir
        // camera AmbientLight
        // camera DiffuseLight
        if (objects != null) {
            for (Scene.MeshReference ob : objects) {

                glBindVertexArray(ob.meshId);
                enable();

                OpenGLUtil.bindBuffer(this.programId, "aVertex", ob.vertexArray, GL_FLOAT);
//                OpenGLUtil.setUniform(this.programId, "aColor", new Vector3f(1f, 1f, 1f));

                if (ob.texture != null) {
                    OpenGLUtil.bindBuffer(this.programId, "aTexCoord", ob.texture.texCoord, GL_FLOAT);
                }

                OpenGLUtil.setUniform(this.programId, "uUseTexture", ob.texture != null);

                if (ob.object.getMaterial() != null) {

                    final Material material = ob.object.getMaterial();

                    if (ob.texture != null) {
                        glActiveTexture(GL_TEXTURE0 + texCount);
                        glBindTexture(GL_TEXTURE_2D, ob.texture.id);
                        OpenGLUtil.setUniform(this.programId, "uTexture",texCount);
                        glBindTexture(GL_TEXTURE_2D, 0);
                        texCount++;
                    }
                }

                // Temos Index buffer?
                if (!ob.indexBuffer.validIndexBuffer()) {

                    // Nao
                    glDrawArrays(GL_TRIANGLES, 0, ob.vertexArray.elementCount);
                } else {

                    // Faz o bind no indexBuffer
                    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ob.indexBuffer.id);
                    // Escreve o array.
                    glDrawElements(GL_TRIANGLES, ob.indexBuffer.elementCount, GL_UNSIGNED_INT, 0);

                    // Unbind
                    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

                }

                OpenGLUtil.unbindBuffer(this.programId, "aVertex", null, GL_FLOAT);
                OpenGLUtil.unbindBuffer(this.programId, "aColor", null, GL_FLOAT);
//                OpenGLUtil.setUniform(this.programId, "aPosition", ob.object.getPosition());

                disable();
                glBindVertexArray(0);

                // Set vertex values of objects
            }
        }

        return this;
    }

}
