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
import org.jphysics.api.GameObject;
import org.jphysics.api.PhysicObject;
import org.jphysics.math.Vector3f;

/**
 *
 * @author luis
 * @param <T>
 */
public interface Object3D<T extends Object3D> extends PhysicObject {

    Mesh getMesh();

    Material getMaterial();

    @Override
    T setPosition(Vector3f position);

    Matrix4f getTransform();
}