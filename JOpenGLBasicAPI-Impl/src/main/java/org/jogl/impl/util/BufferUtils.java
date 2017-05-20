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
import java.util.List;
import org.jogl.api.Vertex;
import org.joml.Vector3f;

/**
 *
 * @author luis
 */
public class BufferUtils {
    public static FloatBuffer convert(List<Vertex> vertices){
        
        final int width = vertices.size() * 9;
        
        final float[] vertexData = new float[width];
        
        int idx = 0;
        for(Vertex v:vertices){
            for(Vector3f vf :v){
                vertexData[idx++] = vf.x;
                vertexData[idx++] = vf.y;
                vertexData[idx++] = vf.z;
            }
        }
        
        final FloatBuffer buffer = org.lwjgl.BufferUtils.createFloatBuffer(vertexData.length);
        buffer.put(vertexData).flip();
        
        return buffer;

    }
}
