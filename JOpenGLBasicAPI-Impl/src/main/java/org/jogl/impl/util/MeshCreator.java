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
package org.jogl.impl.util;

import java.util.ArrayList;
import java.util.List;
import org.jogl.api.Mesh;
import static org.jogl.impl.util.Util.*;
import org.jphysics.math.Vector3f;

/**
 *
 * @author luis
 */
public class MeshCreator {

    private int lastPath = -1;
    private int beforeLastPath = -1;
    private int closedPathSize = 0;
    private final List<Vector3f> vectors;
    private final List<Integer> indexBuffer;
    private boolean optimizeIndexBuffer = true;

    public MeshCreator() {
        vectors = new ArrayList<>();
        indexBuffer = new ArrayList<>();
    }

    public MeshCreator setOptimizeIndexBuffer(boolean optimizeIndexBuffer) {
        this.optimizeIndexBuffer = optimizeIndexBuffer;
        return this;
    }

    public MeshCreator to(float x, float y, float z) {
        return to(new Vector3f(x, y, z));
    }

    /**
     * Start or continue the triangle.
     *
     * @param vector
     * @return
     */
    public MeshCreator to(Vector3f vector) {
        final int curIdx;

        // Make sure that we are using a copy from the vector (help the use, because can reutilize the same with their logic)
        vector = new Vector3f(vector);
        if (optimizeIndexBuffer) {
            if (vectors.contains(vector)) {
                // Ja temos este vetor na lista, logo, vamos apenas adicionar a sua posicao para o index buffer.
                curIdx = vectors.indexOf(vector);
            } else {
                curIdx = vectors.size();
                vectors.add(vector);
            }

            if (beforeLastPath > -1) {
                indexBuffer.add(beforeLastPath);
                indexBuffer.add(lastPath);
                indexBuffer.add(curIdx);
                closedPathSize += 2;
            } else {
                closedPathSize++;
            }

            beforeLastPath = lastPath;
            lastPath = curIdx;
        } else {

            if (closedPathSize  > 2 && beforeLastPath != -1) {
                // Before (-2)
                indexBuffer.add(vectors.size());
                vectors.add(vectors.get(beforeLastPath));

                // Before (-1)
                indexBuffer.add(vectors.size());
                vectors.add(vectors.get(lastPath));
                closedPathSize += 2;
            }

            // Current (0)
            curIdx = vectors.size();
            indexBuffer.add(curIdx);
            vectors.add(vector);

            beforeLastPath = lastPath;
            lastPath = curIdx;
            closedPathSize++;

        }

        return this;
    }

    public MeshCreator flipTo(Vector3f vector) {
        final int curIdx;

        // Make sure that we are using a copy from the vector (help the use, because can reutilize the same with their logic)
        vector = new Vector3f(vector);

        if (optimizeIndexBuffer) {
            if (vectors.contains(vector)) {
                // Ja temos este vetor na lista, logo, vamos apenas adicionar a sua posicao para o index buffer.
                curIdx = vectors.indexOf(vector);

                if (vectors.size() > 3) {
                    indexBuffer.add(indexBuffer.get(indexBuffer.size() - 3));
                    indexBuffer.add(lastPath);
                    indexBuffer.add(curIdx);
                    closedPathSize += 3;
                } else {
                    closedPathSize++;
                }

            } else {
                closedPathSize++;
                curIdx = vectors.size();
                vectors.add(vector);
            }

            beforeLastPath = lastPath;
            lastPath = curIdx;
        } else {
            if (closedPathSize > 2 && lastPath != -1) {

                // Before (-2)
                final int before = indexBuffer.size() - 3;
                indexBuffer.add(vectors.size());
                vectors.add(vectors.get(before));

                // Before (-1)
                indexBuffer.add(vectors.size());
                vectors.add(vectors.get(lastPath));
                // Current (0)
                curIdx = vectors.size();
                indexBuffer.add(curIdx);
                vectors.add(vector);

                beforeLastPath = before;
                lastPath = curIdx;
                closedPathSize += 3;
            } else {
                // Current (0)
                curIdx = vectors.size();
                indexBuffer.add(curIdx);
                vectors.add(vector);

                beforeLastPath = lastPath;
                lastPath = curIdx;
                closedPathSize++;
            }
        }

        return this;
    }

    public MeshCreator copyTo(Float x, Float y, Float z) {

        x = ou(x, 1f);// Default
        y = ou(y, 1f);// Default
        z = ou(z, 1f);// Default

        for (Vector3f v : new ArrayList<>(vectors)) {
            final Vector3f n = new Vector3f(v);
            n.x = x * n.x;
            n.y = y * n.y;
            n.z = z * n.z;

            indexBuffer.add(vectors.size());
            vectors.add(n);

        }

        return this;
    }

    /**
     * Start or continue the triangle.
     *
     * @param vector
     * @return
     */
    public MeshCreator from(Vector3f vector) {
        close();
        return to(vector);
    }

    /**
     * Finish current triangule and start a new one.
     *
     * @return
     */
    public MeshCreator close() {
        beforeLastPath = -1;
        lastPath = -1;
        closedPathSize = 0;
        return this;
    }

    public Mesh create() {
        return new Mesh(vectors, indexBuffer);
    }

    @Override
    public String toString() {

        final StringBuilder b = new StringBuilder();

        b.append("Vectors:\n");

        vectors.forEach((v) -> {
            b.append("\t").append(format(v)).append("\n");
        });

        b.append("IndexBuffer:\n");

        indexBuffer.forEach((v) -> {
            b.append("\t").append(v).append("\n");
        });

        return b.toString();
    }

}
