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

import java.util.ArrayList;
import java.util.List;
import org.jogl.api.Material;
import org.jogl.api.Mesh;
import org.jogl.api.Object3D;
import org.joml.Matrix4f;
import org.jphysics.api.PhysicObject;
import org.jphysics.math.Vector3f;

/**
 *
 * @author luis
 */
abstract class AbstractObject<A extends AbstractObject> implements Object3D<A> {

    protected Mesh mesh;
    protected Material material;
    protected Vector3f position = new Vector3f();
    protected Matrix4f transform = new Matrix4f().identity().rotateY(0.1f);
    protected boolean alive = true;
    protected float mass = 1f;

    private Vector3f velocity = new Vector3f();
    private Vector3f direction = new Vector3f();
    private Vector3f scale = new Vector3f(1f);

    private float radius;
    private float maxVelocity;
    private List<PhysicObject> children = new ArrayList<>();
    private PhysicObject parent;

    @Override
    public Vector3f getPosition() {
        return new Vector3f(position);
    }

    @Override
    public A setPosition(Vector3f position) {
        this.position = position;
        return (A) this;
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

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    public A setAlive(boolean alive) {
        this.alive = alive;
        return (A) this;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" + "position=" + position + ", transform=" + transform + '}';
    }

    @Override
    public void update(float secs) {
    }

    @Override
    public float getMass() {
        return mass;
    }

    @Override
    public PhysicObject decreaseLife() {
        // Nothing to do so far
        return this;
    }

    @Override
    public List<PhysicObject> getChildren() {
        return children;
    }

    @Override
    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public Vector3f getDirection() {
        return new Vector3f(direction);
    }

    @Override
    public float getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(float maxVelocity) {
        this.maxVelocity = maxVelocity;
    }
    
    @Override
    public Matrix4f getTransform() {
        return transform;
    }

    public void setTransform(Matrix4f transform) {
        this.transform = transform;
    }

    @Override
    public Vector3f getVelocity() {
        return new Vector3f(velocity);
    }

    @Override
    public AbstractObject setVelocity(Vector3f velocity) {
        this.velocity = velocity;
        return this;
    }

    @Override
    public Vector3f getScale() {
        return new Vector3f(scale);
    }

    public void scale(Vector3f scale) {
        this.scale.add(scale);
        getTransform().scale(scale);
    }

    @Override
    public PhysicObject getParent() {
        return parent;
    }

    @Override
    public PhysicObject setParent(PhysicObject parent) {
        this.parent = parent;
        return this;
    }

    @Override
    public AbstractObject setDirection(Vector3f direction) {
        this.direction = direction;
        return this;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

}
