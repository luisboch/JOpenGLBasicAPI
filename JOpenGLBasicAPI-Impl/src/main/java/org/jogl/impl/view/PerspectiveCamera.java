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
package org.jogl.impl.view;

import org.jogl.api.Camera;
import org.jogl.impl.util.OpenGLUtil;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author luis
 */
public class PerspectiveCamera implements Camera {

    private final Vector3f position;
    private final Vector3f up;
    private final Vector3f target;
    private final float fov;
    private final float near;
    private final float far;

    public PerspectiveCamera() {
        this.far = 1000.0f;
        this.near = 0.1f;
        this.fov = (float) Math.toRadians(60);
        this.target = new Vector3f(0, 0, 0);
        this.up = new Vector3f(0, 1, 0);
        this.position = new Vector3f(0, 0, 2);
    }

    public PerspectiveCamera(Vector3f position, Vector3f up, Vector3f target, float fov, float near, float far) {
        this.position = position;
        this.up = up;
        this.target = target;
        this.fov = fov;
        this.near = near;
        this.far = far;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getUp() {
        return up;
    }

    public Vector3f getTarget() {
        return target;
    }

    public float getFov() {
        return fov;
    }

    public float getNear() {
        return near;
    }

    public float getFar() {
        return far;
    }

    private float getAspect() {
        final Vector2f screenSize = OpenGLUtil.getScreenSize();
        return screenSize.x / screenSize.y;
    }

    @Override
    public Matrix4f getViewMatrix() {
        return new Matrix4f().lookAt(position, target, up);
    }

    @Override
    public Matrix4f getProjectionMatrix() {
        return new Matrix4f().perspective(fov, getAspect(), near, far);
    }

}
