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
import org.jogl.impl.util.MeshCreator;
import org.jogl.impl.util.Util;
import org.jogl.materials.SmoothMaterial;
import org.joml.Vector3f;

/**
 *
 * @author luis
 */
public class Triangle extends AbstractObject<Triangle> {

    public Triangle() {
        
        mesh = new MeshCreator()
                .to(new Vector3f(0.0f, 0.5f, 0f))
                .to(new Vector3f(-0.5f, -0.5f, 0f))
                .to(new Vector3f(0.5f, -0.5f, 0f))
                .create();
        
        material = new SmoothMaterial(Util.convert(Color.GREEN));
    }
}
