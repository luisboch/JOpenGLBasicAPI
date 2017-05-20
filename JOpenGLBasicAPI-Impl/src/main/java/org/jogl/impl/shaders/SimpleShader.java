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
import org.jogl.impl.util.OpenGLUtil;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author luis
 */
public class SimpleShader extends AbstractShader{
    
    private static final String VERTEX_SHADER
            = "#version 330\n"
            + "in vec3 aVertex;\n"
            + "void main(){\n"
            + "     gl_Position = vec4(aVertex, 1.0);\n"
            + "}";

    private static final String FRAGMENT_SHADER
            = "#version 330\n"
            
            + "out vec4 out_color;\n"
            + "void main(){\n"
            + "     out_color = vec4(1.0, 1.0, 0.0, 1.0);\n"
            + "}";

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
        if(objects != null){
            objects.forEach((Scene.MeshReference ob)-> {
                GL30.glBindVertexArray(ob.meshId);
                if(ob.object.getMaterial() != null){
                    // CODE FOR MATERIAL
                    final Material material = ob.object.getMaterial();
                    // Material uniforms
                    // uAmbientMaterial
                    // uDiffuseMaterial
                    // uSpecularMaterial
                    // uSpecularPower
                    
                    if(material.getColor() != null){
                        OpenGLUtil.setUniform(this.programId, "aColor", material.getColor());
                    }
                }
                
                drawFloatArray("aVertex", ob.array);
                OpenGLUtil.setUniform(this.programId, "aPosition", ob.object.getPosition());
                GL30.glBindVertexArray(0);
                // Set vertex values of objects
            });
        }
        
        
        
        return this;
    }
    

//    @Override
//    public Shader render() {
//        
//        glUseProgram(programId);
//        glBindVertexArray(vao);
//        
//        int aPosition = glGetAttribLocation(shader, "aPosition");
//        glEnableVertexAttribArray(aPosition);
//        glBindBuffer(GL_ARRAY_BUFFER, positions);
//        glVertexAttribPointer(aPosition, 2, GL_FLOAT, false, 0, 0);
//        glDrawArrays(GL_TRIANGLES, 0, 3);
//        
//        glDisableVertexAttribArray(aPosition);
//        glBindBuffer(GL_ARRAY_BUFFER, 0);
//        glBindVertexArray(0);
//        
//        
//    }
//    

}
