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
import org.jogl.api.GlobalLight;
import org.jogl.api.Material;
import org.jogl.api.Scene;
import org.jogl.api.Shader;
import org.jogl.impl.util.FileUtil;
import org.jogl.impl.util.OpenGLUtil;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
 *
 * @author luis
 */
public class SimpleShader extends AbstractShader {

    private static final String PATH;

    static {
        PATH = SimpleShader.class.getClassLoader().getResource("shaders/").getPath();
    }

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
    public Shader render(List<Scene.MeshReference> objects, List<GlobalLight> light) {

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
            objects.forEach((Scene.MeshReference ob) -> {

                glBindVertexArray(ob.meshId);

                if (ob.object.getMaterial() != null) {
                    // CODE FOR MATERIAL
                    final Material material = ob.object.getMaterial();
                    // Material uniforms
                    // uAmbientMaterial
                    // uDiffuseMaterial
                    // uSpecularMaterial
                    // uSpecularPower

                    if (material.getColor() != null) {
                        OpenGLUtil.setUniform(this.programId, "aColor", material.getColor());
                    }
                }

                drawFloatArray("aVertex", ob.vertexArray);
                OpenGLUtil.setUniform(this.programId, "aPosition", ob.object.getPosition());

                glBindVertexArray(0);
                // Set vertex values of objects
            });
        }

        return this;
    }

}
