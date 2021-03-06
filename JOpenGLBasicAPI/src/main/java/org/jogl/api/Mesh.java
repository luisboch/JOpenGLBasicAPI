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

import java.util.ArrayList;
import java.util.List;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author luis
 */
public class Mesh {

    private final List<Vector3f> vertices = new ArrayList<>();
    private final List<Integer> indexBuffer = new ArrayList<>();

    private final List<Vector3f> normals = new ArrayList<>();
    private final List<Vector2f> texturePos = new ArrayList<>();

    public Mesh() {
    }

    public Mesh(List<Vector3f> vertices, List<Integer> indexBuffer) {
        this();

        this.vertices.addAll(vertices);
        this.indexBuffer.addAll(indexBuffer);
    }

    public Mesh(List<Vector3f> vertices, List<Integer> indexBuffer, List<Vector3f> normals) {
        this();
        this.vertices.addAll(vertices);
        this.indexBuffer.addAll(indexBuffer);
        this.normals.addAll(normals);
    }

    public List<Vector3f> getVertices() {
        return vertices;
    }

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public List<Vector3f> getNormals() {
        return normals;
    }

    public List<Vector2f> getTexturePos() {
        return texturePos;
    }

    
}
