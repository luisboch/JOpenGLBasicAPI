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

import org.jogl.impl.util.OpenGLUtil;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author luis
 */
public class OrthographicCamera extends AbstractCamera {
    

    public OrthographicCamera() {
        this(new Vector3f(0,0,2), new Vector3f(0, 1, 0),  new Vector3f(0,0,-2),  0.1f, 1000.0f);
    }

    public OrthographicCamera(Vector3f position, Vector3f up, Vector3f target,float near, float far) {
       super(position, up, target, near, far);
    }
    
    @Override
    public Matrix4f getViewMatrix() {
        return new Matrix4f().lookAt(position, target, up);
    }

    @Override
    public Matrix4f getProjectionMatrix() {
        final Vector2f screenSize = OpenGLUtil.getScreenSize();
        final float w = screenSize.x * 0.5f;
        final float h = screenSize.y * 0.5f;
        return new Matrix4f().ortho(-w , w , -h, h, near, far );
    }
    
    
    
}
