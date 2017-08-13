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
package org.jogl.api;

import org.joml.Matrix4f;
import org.jphysics.math.Vector3f;

/**
 *
 * @author luis
 */
public interface Camera {

    Matrix4f getProjectionMatrix();

    Matrix4f getViewMatrix();

    Vector3f getPosition();

    Vector3f getTarget();

    public default Camera moveToFront(float secs) {
        Vector3f sum = new Vector3f(getDirection()).normalize().mul(secs);
        getPosition().add(sum);
        getTarget().set(new Vector3f(getPosition()).add(getDirection()));
        return this;
    }

    public default Camera moveToRear(float secs) {
        Vector3f sum = new Vector3f(getDirection()).normalize().mul(secs);
        getPosition().sub(sum);
        getTarget().set(new Vector3f(getPosition()).add(getDirection()));
        return this;
    }

    public default Camera rotateLeft(float secs) {
        getDirection().set(new Matrix4f().rotateY((float) Math.toRadians(30d) * secs).transformDirection(getDirection()));
        getTarget().set(new Vector3f(getPosition()).add(getDirection()));
        return this;
    }

    default Camera lookDown(float secs) {
        getDirection().set(new Matrix4f().rotateX((float) Math.toRadians(30d) * secs * -1).transformDirection(getDirection()));
        getTarget().set(new Vector3f(getPosition()).add(getDirection()));
        return this;
    }

    default Camera lookUP(float secs) {
        getDirection().set(new Matrix4f().rotateX((float) Math.toRadians(30d) * secs).transformDirection(getDirection()));
        getTarget().set(new Vector3f(getPosition()).add(getDirection()));
        return this;
    }

    public default Camera rotateRight(float secs) {
        getDirection().set(new Matrix4f().rotateY((float) Math.toRadians(30d) * secs * -1).transformDirection(getDirection()));
        getTarget().set(new Vector3f(getPosition()).add(getDirection()));
        return this;
    }

    public default Camera strafeRight(float secs) {
        Vector3f cross = new Vector3f(getUp()).cross(getDirection()).normalize();
        getPosition().sub(cross.mul(secs));
        getTarget().set(new Vector3f(getPosition()).add(getDirection()));
        return this;
    }

    public default Camera strafeLeft(float secs) {
        Vector3f cross = new Vector3f(getUp()).cross(getDirection()).normalize();
        getPosition().add(cross.mul(secs));
        getTarget().set(new Vector3f(getPosition()).add(getDirection()));
        return this;
    }

    public Vector3f getUp();

    public Vector3f getDirection();

    default Camera setDirection(Vector3f dir) {
        getDirection().set(dir);
        getTarget().set(new Vector3f(getPosition()).add(getDirection()));
        return this;
    }

    default Camera setPosition(Vector3f pos) {
        getPosition().set(pos);
        getTarget().set(new Vector3f(getPosition()).add(getDirection()));
        return this;
    }

}
