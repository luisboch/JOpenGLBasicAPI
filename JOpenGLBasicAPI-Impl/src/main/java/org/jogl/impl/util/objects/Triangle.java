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
package org.jogl.impl.util.objects;

import java.awt.Color;
import org.jogl.api.LocalLight;
import org.jogl.api.Material;
import org.jogl.api.Mesh;
import org.jogl.api.Object3D;
import org.jogl.api.Vertex;
import org.jogl.impl.util.Util;
import org.jogl.materials.SmoothMaterial;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 *
 * @author luis
 */
public class Triangle implements Object3D<Triangle> {

    private Mesh mesh;
    private LocalLight light;
    private Material material;
    
    private Vector3f position = new Vector3f();
    
    public Triangle() {
        mesh = new Mesh().addVertice(
                new Vertex(
                        new Vector3f(0.0f, 0.5f, 1.0f),
                        new Vector3f(-0.5f, -0.5f, 1.0f),
                        new Vector3f( 0.5f, -0.5f, 1.0f)));
        material = new SmoothMaterial(Util.convert(Color.GREEN));
    }

    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public Triangle setPosition(Vector3f position) {
        this.position = position;
        return this;
    }
    
    @Override
    public Matrix4f getTransform() {
        return new Matrix4f().identity();
    }

    @Override
    public Mesh getMesh() {
        return mesh;
    }

    @Override
    public LocalLight getLight() {
        return light;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    public Triangle setMaterial(Material material) {
        this.material = material;
        return this;
    }
    
}
