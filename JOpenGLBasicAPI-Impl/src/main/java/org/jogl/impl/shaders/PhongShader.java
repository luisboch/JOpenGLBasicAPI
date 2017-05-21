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
import org.jogl.api.PhongMaterial;
import org.jogl.api.Scene;
import org.jogl.api.Shader;
import org.jogl.impl.util.FileUtil;
import org.jogl.impl.util.OpenGLUtil;
import org.joml.Matrix4f;
import org.joml.Vector3f;
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

        /*
        
        //FRAG UNIFORMS
        uniform vec3 uLightDir;

        uniform vec3 uAmbientLight;
        uniform vec3 uDiffuseLight;
        uniform vec3 uSpecularLight;

        uniform vec3 uAmbientMaterial;
        uniform vec3 uDiffuseMaterial;
        uniform vec3 uSpecularMaterial;

        uniform float uSpecularPower;

         */
        final Matrix4f world = new Matrix4f().identity();

        OpenGLUtil.setUniform(this.programId, "uProjection", camera.getProjectionMatrix());
        OpenGLUtil.setUniform(this.programId, "uView", camera.getViewMatrix());
        OpenGLUtil.setUniform(this.programId, "uWorld", world);
        OpenGLUtil.setUniform(this.programId, "uCameraPosition", camera.getPosition());

        OpenGLUtil.setUniform(this.programId, "uLightDir", new Vector3f(1.0f, -3.0f, -1.0f));
        OpenGLUtil.setUniform(this.programId, "uAmbientLight", new Vector3f(0.02f, 0.02f, 0.02f));
        OpenGLUtil.setUniform(this.programId, "uDiffuseLight", new Vector3f(1.0f, 1.0f, 1.0f));
        OpenGLUtil.setUniform(this.programId, "uSpecularLight", new Vector3f(1.0f, 1.0f, 1.0f));

        if (objects != null) {
            objects.forEach((Scene.MeshReference ob) -> {

                glBindVertexArray(ob.meshId);

                if (ob.object.getMaterial() != null) {
                    // CODE FOR MATERIAL
                    final Material material = ob.object.getMaterial();

                    if (material instanceof PhongMaterial) {
                        PhongMaterial phongMaterial = (PhongMaterial) material;

                        OpenGLUtil.setUniform(this.programId, "uAmbientMaterial", phongMaterial.getAmbientMaterial());
                        OpenGLUtil.setUniform(this.programId, "uDiffuseMaterial", phongMaterial.getDiffuseMaterial());
                        OpenGLUtil.setUniform(this.programId, "uSpecularMaterial", phongMaterial.getSpecularMaterial());
                        OpenGLUtil.setUniform(this.programId, "uSpecularPower", phongMaterial.getSpecularPower());

                    }

                    if (material.getColor() != null) {
                        OpenGLUtil.setUniform(this.programId, "uUseColor", true);
                        OpenGLUtil.setUniform(this.programId, "uColor", material.getColor());
                    }
                    
                }

                drawFloatArray("aVertex", ob.vertexArray);
                drawFloatArray("aNormal", ob.normalArray);
                OpenGLUtil.setUniform(this.programId, "uPosition", ob.object.getPosition());
                OpenGLUtil.setUniform(this.programId, "uTransform", ob.object.getTransform());

                glBindVertexArray(0);
                // Set vertex values of objects
            });
        }

        return this;
    }

}
