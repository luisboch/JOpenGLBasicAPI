/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jogl.api;

import java.util.List;

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
    Shader render(List<Scene.MeshReference> objects, List<GlobalLight> light);
    
    /**
     * Enable this render on GPU (use program cmd)
     * @return 
     */
    Shader disable();
    
    boolean isCompiled();
}
