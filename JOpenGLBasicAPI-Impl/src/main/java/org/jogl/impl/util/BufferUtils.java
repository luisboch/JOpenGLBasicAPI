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

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 *
 * @author luis
 */
public class BufferUtils {

    public static IntBuffer convertIndexBuffer(List<Integer> buffer) {


        final int[] vertexData = new int[buffer.size()];
        
        for(int i =0; i < buffer.size(); i++){
            vertexData[i] = buffer.get(i);
        }
        
        final IntBuffer intBuffer = org.lwjgl.BufferUtils.createIntBuffer(vertexData.length);
        intBuffer.put(vertexData).flip();

        return intBuffer;

    }
    
    public static FloatBuffer convert(List<Vector3f> vertices) {

        final int width = vertices.size() * 3;

        final float[] vertexData = new float[width];

        int idx = 0;

        for (Vector3f v : vertices) {
            vertexData[idx++] = v.x;
            vertexData[idx++] = v.y;
            vertexData[idx++] = v.z;
        }

        final FloatBuffer buffer = org.lwjgl.BufferUtils.createFloatBuffer(vertexData.length);
        buffer.put(vertexData).flip();

        return buffer;

    }
    
    public static FloatBuffer convertVec2f(List<Vector2f> vertices) {

        final int width = vertices.size() * 2;

        final float[] vertexData = new float[width];

        int idx = 0;

        for (Vector2f v : vertices) {
            vertexData[idx++] = v.x;
            vertexData[idx++] = v.y;
        }

        final FloatBuffer buffer = org.lwjgl.BufferUtils.createFloatBuffer(vertexData.length);
        buffer.put(vertexData).flip();

        return buffer;

    }

    public static FloatBuffer convertTo2d(List<Vector3f> vertices) {

        final int width = vertices.size() * 2;

        final float[] vertexData = new float[width];

        int idx = 0;
        
        for (Vector3f v : vertices) {
            vertexData[idx++] = v.x;
            vertexData[idx++] = v.y;
        }

        final FloatBuffer buffer = org.lwjgl.BufferUtils.createFloatBuffer(vertexData.length);
        buffer.put(vertexData).flip();

        return buffer;

    }
}
