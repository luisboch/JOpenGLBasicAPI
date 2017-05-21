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

import org.jogl.api.Material;
import org.jogl.api.Mesh;
import org.jogl.api.Object3D;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 *
 * @author luis
 */
abstract class AbstractObject<A> implements Object3D<A>{
    
    protected Mesh mesh;
    protected Material material;
    protected Vector3f position = new Vector3f();
    protected Matrix4f transformation = new Matrix4f().identity();
    
    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public A setPosition(Vector3f position) {
        this.position = position;
        return (A) this;
    }
    
    
    @Override
    public Matrix4f getTransform() {
        return transformation;
    }

    @Override
    public Mesh getMesh() {
        return mesh;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    public A setMaterial(Material material) {
        this.material = material;
        return (A) this;
    }
    
    
}
