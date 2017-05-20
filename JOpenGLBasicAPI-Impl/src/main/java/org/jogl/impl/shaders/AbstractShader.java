/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jogl.impl.shaders;

import org.jogl.api.Camera;
import org.jogl.api.Scene;
import org.jogl.api.Shader;
import org.joml.Matrix4f;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 *
 * @author luis
 */
public abstract class AbstractShader implements Shader {

    /**
     * This Instance references.
     */
    protected int programId;
    protected int vertexShaderId;
    protected int fragmentShaderId;
    private boolean compiled = false;
    
    private Camera camera;

    @Override
    public Shader setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    @Override
    public Shader compile() throws Exception {
        return createProgram();
    }

    /**
     * Compile vertex and fragment Shaders,before finish assing the following
     * attrs:
     * <ul>
     * <li>{@link #vertexShaderId}</li>
     * <li>{@link #fragmentShaderId}</li>
     * <li>{@link #programId}</li>
     * </ul>
     *
     * @return this instance
     */
    protected Shader createProgram() {
        
        if(isCompiled()){
            // Already compiled
            return this;
        }
        
        this.vertexShaderId = compileVertexShader();
        this.fragmentShaderId = compileFragmentShader();
        this.programId = linkProgram(this.vertexShaderId, this.fragmentShaderId);
        this.compiled = true;
        return this;
    }

    /**
     * Compile fragment Shader using FragmentShader code
     *
     * @return
     */
    protected int compileFragmentShader() {
        return compileShader(GL_FRAGMENT_SHADER, getFragmetShaderCode());
    }

    /**
     * Compile vertex Shader using VertexShader code
     *
     * @return
     */
    protected int compileVertexShader() {
        return compileShader(GL_VERTEX_SHADER, getVertexShaderCode());
    }

    /**
     * Must return VertexShader code, (Loaded from disk or simple string)
     *
     * @return this instance
     */
    protected abstract String getVertexShaderCode();

    /**
     * Must return FragmentShader code, (Loaded from disk or simple string)
     *
     * @return this instance
     */
    protected abstract String getFragmetShaderCode();

    private int compileShader(int shaderType, String code) {
        int shader = glCreateShader(shaderType);
        glShaderSource(shader, code);
        glCompileShader(shader);

        if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new RuntimeException("Unable to compile shader: " + glGetShaderInfoLog(shader));
        }
        return shader;
    }

    private int linkProgram(int... shaders) {
        int program = glCreateProgram();

        for (int s : shaders) {
            glAttachShader(program, s);
        }

        glLinkProgram(program);

        if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
            throw new RuntimeException("Unable to link program: " + glGetProgramInfoLog(program));
        }

        for (int s : shaders) {
            glDetachShader(program, s);
            glDeleteShader(s);
        }
        return program;

    }

    @Override
    public Shader enable() {
        glUseProgram(programId);
        return this;
    }

    @Override
    public Shader disable() {
        glUseProgram(0);
        return this;
    }
    
    protected void draw(String attribName, Scene.ArrayBuffer buffer, int glType) {
        int attrID = glGetAttribLocation(this.programId, attribName);
        glEnableVertexAttribArray(attrID);
        glBindBuffer(GL_ARRAY_BUFFER, buffer.id);
        glVertexAttribPointer(attrID, buffer.elementSize , glType, false, 0, 0);
        glDrawArrays(GL_TRIANGLES, 0, buffer.elementCount);

        glDisableVertexAttribArray(attrID);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

    }
    protected void drawFloatArray(String attribName, Scene.ArrayBuffer buffer) {
        draw(attribName, buffer, GL_FLOAT);

    }

    @Override
    public boolean isCompiled() {
        return compiled;
    }
    
    
}
