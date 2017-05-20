/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jogl.materials;

import org.jogl.api.Material;
import org.jogl.api.Shader;
import org.jogl.api.Texture;
import org.joml.Vector3f;

/**
 *
 * @author luis
 */
public class SmoothMaterial implements Material {
    
    private final Vector3f color;

    public SmoothMaterial() {
        this.color = new Vector3f();
    }

    public SmoothMaterial(Vector3f color) {
        this.color = color;
    }
    
    @Override
    public Shader getShader() {
        return null;
    }

    @Override
    public Texture getTexture() {
        return null;
    }

    @Override
    public Vector3f getColor() {
        return color;
    }
    
}
