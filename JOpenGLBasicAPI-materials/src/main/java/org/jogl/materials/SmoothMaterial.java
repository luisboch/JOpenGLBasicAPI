/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jogl.materials;

import org.jogl.api.Material;
import org.jogl.api.PhongMaterial;
import org.jogl.api.Shader;
import org.jogl.api.Texture;
import org.joml.Vector3f;

/**
 *
 * @author luis
 */
public class SmoothMaterial implements PhongMaterial {

    private final Vector3f ambientMaterial;
    private final Vector3f diffuseMaterial;
    private final Vector3f specularMaterial;
    private final float specularPower;


    private final Vector3f color;

    public SmoothMaterial() {
        this(new Vector3f());
    }

    public SmoothMaterial(Vector3f color) {
        this(new Vector3f(1.0f, 1.0f, 1.0f), new Vector3f(0.7f, 0.7f, 0.7f), new Vector3f(1.0f, 1.0f, 1.0f), 2.0f, color);
    }

    public SmoothMaterial(Vector3f ambientMaterial, Vector3f diffuseMaterial, Vector3f specularMaterial, float specularPower, Vector3f color) {
        this.ambientMaterial = ambientMaterial;
        this.diffuseMaterial = diffuseMaterial;
        this.specularMaterial = specularMaterial;
        this.specularPower = specularPower;
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

    @Override
    public Vector3f getAmbientMaterial() {
        return ambientMaterial;
    }

    @Override
    public Vector3f getDiffuseMaterial() {
        return diffuseMaterial;
    }

    @Override
    public Vector3f getSpecularMaterial() {
        return specularMaterial;
    }

    @Override
    public float getSpecularPower() {
        return specularPower;
    }

}
