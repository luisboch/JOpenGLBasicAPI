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

import java.util.List;
import org.jogl.api.Mesh;
import org.joml.Vector2f;
import org.jphysics.api.PhysicObject;
import org.jphysics.math.Vector3f;

/**
 *
 * @author luis
 */
public class Square extends AbstractObject<Square> {

    public Square() {
//        mesh = new MeshCreator()
//                .setOptimizeIndexBuffer(false)
//                .to(new Vector3f(-0.5f, 0.5f, 0f))
//                .to(new Vector3f(-0.5f, -0.5f, 0f))
//                .to(new Vector3f(0.5f, -0.5f, 0f))
//                .flipTo(new Vector3f(0.5f, 0.5f, 0f))
//                .create();
//        
//        
//        mesh.getTexturePos().add(new Vector2f(0f, 0f));
//        mesh.getTexturePos().add(new Vector2f(0f, 1f));
//        mesh.getTexturePos().add(new Vector2f(1f, 1f));
//        mesh.getTexturePos().add(new Vector2f(0f, 0f));
//        mesh.getTexturePos().add(new Vector2f(1f, 1f));
//        mesh.getTexturePos().add(new Vector2f(1f, 0f));
        mesh = new Mesh();

        List<Vector3f> vertices = mesh.getVertices();
        vertices.add(new Vector3f(-0.5f, 0.5f, 0.0f)); // 0
        vertices.add(new Vector3f(0.5f, 0.5f, 0.0f)); // 1
        vertices.add(new Vector3f(-0.5f, -0.5f, 0.0f)); // 2
        vertices.add(new Vector3f(0.5f, -0.5f, 0.0f)); // 3

        final List<Vector2f> texturePos = mesh.getTexturePos();
        texturePos.add(new Vector2f(0.0f, 0.0f));
        texturePos.add(new Vector2f(1.0f, 0.0f));
        texturePos.add(new Vector2f(0.0f, 1.0f));
        texturePos.add(new Vector2f(1.0f, 1.0f));

        List<Vector3f> normals = mesh.getNormals();
        normals.add(new Vector3f(0f, 0f, 1f));
        normals.add(new Vector3f(0f, 0f, 1f));
        normals.add(new Vector3f(0f, 0f, 1f));
        normals.add(new Vector3f(0f, 0f, 1f));

        List<Integer> idx = mesh.getIndexBuffer();
        idx.add(0);
        idx.add(2);
        idx.add(3);
        idx.add(0);
        idx.add(3);
        idx.add(1);
    }
}
