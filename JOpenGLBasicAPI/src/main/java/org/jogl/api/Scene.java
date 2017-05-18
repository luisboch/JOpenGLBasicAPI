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
public interface Scene {
    Shader getShader();
    
    List<GlobalLight> getLights();
    List<Filter> getFilters();
    List<Object3D> getObjects();
}
