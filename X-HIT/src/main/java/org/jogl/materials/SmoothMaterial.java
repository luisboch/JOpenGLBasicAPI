/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jogl.materials;

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
    private Texture texture;

    public SmoothMaterial() {
        this(new Vector3f());
    }

    public SmoothMaterial(Vector3f color) {
        this(color, null);
    }

    public SmoothMaterial(Vector3f color, Texture texture) {
        this(new Vector3f(0.2f, 0.2f, 0.2f), new Vector3f(0.7f, 0.7f, 0.7f), new Vector3f(0.5f, 0.5f, 0.5f), 100.0f, color, texture);
    }

    public SmoothMaterial(Vector3f ambientMaterial, Vector3f diffuseMaterial, Vector3f specularMaterial, float specularPower, Vector3f color, Texture texture) {
        
        if (color != null) {
            this.ambientMaterial = ambientMaterial.mul(color);
            this.diffuseMaterial = diffuseMaterial.mul(color);
            this.specularMaterial = specularMaterial.mul(color);
        } else {
            this.ambientMaterial = ambientMaterial;
            this.diffuseMaterial = diffuseMaterial;
            this.specularMaterial = specularMaterial;
        }
        
        this.specularPower = specularPower;
    }

    @Override
    public Shader getShader() {
        return null;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public SmoothMaterial setTexture(Texture texture) {
        this.texture = texture;
        return this;
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
