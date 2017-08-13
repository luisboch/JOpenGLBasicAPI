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
import org.jogl.api.Mesh;
import org.joml.Vector2f;
import org.jphysics.math.Vector3f;

/**
 *
 * @author luis
 */
public class Sphere extends AbstractObject<Sphere> {

    public Sphere() {
        this(50, 50);
    }

    public Sphere(int slices, int stacks) {
        mesh = new Mesh();

        // Criação dos vértices
        List<Vector3f> vertices = mesh.getVertices();
        List<Vector2f> texCoords = mesh.getTexturePos();

        for (int slice = 0; slice <= slices; slice++) {
            double theta = slice * Math.PI / slices;
            double sinTheta = Math.sin(theta);
            double cosTheta = Math.cos(theta);

            for (int stack = 0; stack <= stacks; stack++) {
                double phi = stack * 2 * Math.PI / stacks;
                double sinPhi = Math.sin(phi);
                double cosPhi = Math.cos(phi);

                float x = (float) (cosPhi * sinTheta);
                float y = (float) (cosTheta);
                float z = (float) (sinPhi * sinTheta);
                float s = 1.0f - (stack / (float) stacks);
                float t = 1.0f - (slice / (float) slices);

                texCoords.add(new Vector2f(s, t));
                vertices.add(new Vector3f(x, y, z));
            }
        }

        //Criação dos índices
        List<Integer> indices = mesh.getIndexBuffer();
        for (int z = 0; z < slices; z++) {
            for (int x = 0; x < stacks; x++) {
                int zero = x + z * stacks;
                int one = (x + 1) + z * stacks;
                int two = x + (z + 1) * stacks;
                int three = (x + 1) + (z + 1) * stacks;

                indices.add(zero);
                indices.add(three);
                indices.add(one);

                indices.add(zero);
                indices.add(two);
                indices.add(three);
            }
        }
        
        for (int slice = 0; slice <= slices; slice++) {
            double theta = slice * Math.PI / slices;
            double sinTheta = Math.sin(theta);
            double cosTheta = Math.cos(theta);

            for (int stack = 0; stack <= stacks; stack++) {
                double phi = stack * 2 * Math.PI / stacks;
                double sinPhi = Math.sin(phi);
                double cosPhi = Math.cos(phi);

                float x = (float) (cosPhi * sinTheta);
                float y = (float) (cosTheta);
                float z = (float) (sinPhi * sinTheta);
                float s = 1.0f - (stack / (float) stacks);
                float t = 1.0f - (slice / (float) slices);

                texCoords.add(new Vector2f(s, t));
                vertices.add(new Vector3f(x, y, z));
            }
        }

    }
}
