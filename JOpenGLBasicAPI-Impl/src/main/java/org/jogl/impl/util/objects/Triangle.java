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
                .to(new Vector3f(0.0f, 0.5f, 0.5f))
                .to(new Vector3f(-0.5f, -0.5f, 0.5f))
                .to(new Vector3f(0.5f, -0.5f, 0.5f))
                .create();
//

//      Metal
//        setMaterial(
//                new SmoothMaterial(
//                        new Vector3f(0.5f, 0.5f, 0.2f), // ambient 
//                        new Vector3f(1.01f, 1.00f, 1.01f), // difuse
//                        new Vector3f(0.1f, 0.1f, 0.1f), // specular material
//                        0.1f, // power
//                        Util.convert(Color.BLUE), // color
//                        null)); // texture
//      

// Shapado
//
//                .setUniform("uDiffuseMaterial", new Vector3f(1f, 0.2f, 1f))
//                .setUniform("uAmbientMaterial", new Vector3f(1f, 1f, 1f))
//                .setUniform("uSpecularMaterial", new Vector3f(1f, 1f, 1f))
        setMaterial(
                new SmoothMaterial(
                        new Vector3f(1f, 1f, 1f), // ambient 
                        new Vector3f(1f, 0.2f, 1f), // difuse
                        new Vector3f(1f, 1f, 1f), // specular material
                        500f, // power
                        Util.convert(Color.BLUE), // color
                        null)); // texture

    }
}
