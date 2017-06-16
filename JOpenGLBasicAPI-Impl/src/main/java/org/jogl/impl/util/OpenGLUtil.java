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
package org.jogl.impl.util;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.jogl.api.Scene;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import static org.lwjgl.glfw.GLFW.glfwGetCurrentContext;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author luis
 */
public class OpenGLUtil {

    public static Vector2f getScreenSize() {
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        long window = glfwGetCurrentContext();
        glfwGetWindowSize(window, w, h);
        return new Vector2f(w.get(), h.get());
    }

    public static void setUniform(int programID, String name, Matrix4f matrix) {
        int uniform = findUniform(programID, name);

        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        matrix.get(buffer);
        glUniformMatrix4fv(uniform, false, buffer);
    }

    public static void setUniform(int programID, String name, float value) {
        int uniform = findUniform(programID, name);
        glUniform1f(uniform, value);
    }

    public static void setUniform(int programID, String name, boolean value) {
        int uniform = findUniform(programID, name);
        glUniform1i(uniform, value ? GL_TRUE : GL_FALSE);
    }

    public static void setUniform(int programID, String name, Matrix3f matrix) {
        int uniform = findUniform(programID, name);

        FloatBuffer buffer = BufferUtils.createFloatBuffer(9);
        matrix.get(buffer);
        glUniformMatrix3fv(uniform, false, buffer);
    }

    public static void setUniform(int programID, String name, Vector3f vector) {
        glUniform3f(findUniform(programID, name), vector.x, vector.y, vector.z);
    }

    public static int findUniform(int programID, String name) {
        
        int uniform = glGetUniformLocation(programID, name);
        
        if (uniform == -1) {
            System.out.println("WARM:  Uniform with name: " + name + " not found...");
        }
        
        return uniform;
    }

    public static void bindBuffer(int programID, String attribName, Scene.ArrayBuffer buffer, int glType){

        int attrID = glGetAttribLocation(programID, attribName);
        glEnableVertexAttribArray(attrID); //0

        // Bind no FloatBuffer
        glBindBuffer(GL_ARRAY_BUFFER, buffer.id); //1
        
        // Aponta para o atributo
        glVertexAttribPointer(attrID, buffer.elementSize , glType, false, 0, 0); //2 , 5126
        
        //Unbind do atributo
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
    }
    public static void unbindBuffer(int programID, String attribName, Scene.ArrayBuffer buffer, int glType){
        int attrID = glGetAttribLocation(programID, attribName);
        // Desabilita o atributo.
        glDisableVertexAttribArray(attrID); //0
        
    }
}
