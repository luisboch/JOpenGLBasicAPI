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
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author luis
 */
abstract class AbstractCamera implements Camera {
    
    protected final Vector3f position;
    protected final Vector3f direction;
    protected final Vector3f up;
    protected final Vector3f target;
    protected final float near;
    protected final float far;

    public AbstractCamera(Vector3f position, Vector3f up, Vector3f direction, float near, float far) {
        this.position = position;
        this.up = up;
        this.near = near;
        this.far = far;
        this.direction = direction;
        this.target = new Vector3f(position).add(direction);
    }

    
    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public Vector3f getUp() {
        return up;
    }

    @Override
    public Vector3f getTarget() {
        return target;
    }

    public float getNear() {
        return near;
    }

    public float getFar() {
        return far;
    }

    @Override
    public Vector3f getDirection() {
        return direction;
    }
    
    public float getAspect() {
        final Vector2f screenSize = OpenGLUtil.getScreenSize();
        return screenSize.x / screenSize.y;
    }

    
    
}
