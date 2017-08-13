/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jogl.api;

import java.util.List;
import org.jogl.api.screen.Scene;

/**
 *
 * @author luis
 */
public interface Shader {
    
    /**
     * Start this shader on GPU·
     * Can be called more than once
     * @throws Exception when error occurs.
     * @return 
     */
    Shader compile() throws Exception;
    Shader setCamera(Camera camera);
    
    /**
     * Enable this render on GPU (use program cmd)
     * @return 
     */
    Shader enable();
    
    /**
     * Just render objects, in this context, the shader is, already enabled.
     * @param objects
     * @param light
     * @return 
     */
    Shader render(List<Scene.MeshReference> objects);
    
    /**
     * Enable this render on GPU (use program cmd)
     * @return 
     */
    Shader disable();
    
    boolean isCompiled();
}
