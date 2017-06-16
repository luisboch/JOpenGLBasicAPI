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

import org.jogl.impl.util.MeshCreator;
import org.joml.Vector3f;

/**
 *
 * @author luis
 */
public class Cube extends AbstractObject<Cube> {

    public Cube() {
        mesh = new MeshCreator()
                //  Front face
                .to(new Vector3f(-0.5f, +0.5f, +0.5f))
                .to(new Vector3f(-0.5f, -0.5f, +0.5f))
                .to(new Vector3f(+0.5f, -0.5f, +0.5f))
                .flipTo(new Vector3f(+0.5f, +0.5f, +0.5f))
                // Not folow path
                .close()
                // Create back face
                .to(new Vector3f(+0.5f, +0.5f, -0.5f))
                .to(new Vector3f(+0.5f, -0.5f, -0.5f))
                .to(new Vector3f(-0.5f, -0.5f, -0.5f))
                .flipTo(new Vector3f(-0.5f, +0.5f, -0.5f))
                .close()
                // Create right face
                .to(new Vector3f(+0.5f, +0.5f, +0.5f))
                .to(new Vector3f(+0.5f, -0.5f, +0.5f))
                .to(new Vector3f(+0.5f, -0.5f, -0.5f))
                .flipTo(new Vector3f(+0.5f, +0.5f, -0.5f))
                .close()
                // Create left face
                .to(new Vector3f(-0.5f, -0.5f, +0.5f))
                .to(new Vector3f(-0.5f, +0.5f, +0.5f))
                .to(new Vector3f(-0.5f, +0.5f, -0.5f))
                .flipTo(new Vector3f(-0.5f, -0.5f, -0.5f))
                .close()
                // Create down face
                .to(new Vector3f(-0.5f, -0.5f, -0.5f))
                .to(new Vector3f(+0.5f, -0.5f, -0.5f))
                .to(new Vector3f(+0.5f, -0.5f, +0.5f))
                .flipTo(new Vector3f(-0.5f, -0.5f, +0.5f))
                .close()
                // Create top face
                .to(new Vector3f(-0.5f, +0.5f, +0.5f))
                .to(new Vector3f(+0.5f, +0.5f, +0.5f))
                .to(new Vector3f(+0.5f, +0.5f, -0.5f))
                .flipTo(new Vector3f(-0.5f, +0.5f, -0.5f))
                //                
                .create();

        // create normals
        mesh.getNormals().add(new Vector3f(0f, 0f, 1f));
        mesh.getNormals().add(new Vector3f(0f, 0f, 1f));
        mesh.getNormals().add(new Vector3f(0f, 0f, 1f));
        mesh.getNormals().add(new Vector3f(0f, 0f, 1f));

        mesh.getNormals().add(new Vector3f(0f, 0f, -1f));
        mesh.getNormals().add(new Vector3f(0f, 0f, -1f));
        mesh.getNormals().add(new Vector3f(0f, 0f, -1f));
        mesh.getNormals().add(new Vector3f(0f, 0f, -1f));

        mesh.getNormals().add(new Vector3f(1f, 0f, 0f));
        mesh.getNormals().add(new Vector3f(1f, 0f, 0f));
        mesh.getNormals().add(new Vector3f(1f, 0f, 0f));
        mesh.getNormals().add(new Vector3f(1f, 0f, 0f));
        
        mesh.getNormals().add(new Vector3f(-1f, 0f, 0f));
        mesh.getNormals().add(new Vector3f(-1f, 0f, 0f));
        mesh.getNormals().add(new Vector3f(-1f, 0f, 0f));
        mesh.getNormals().add(new Vector3f(-1f, 0f, 0f));

        mesh.getNormals().add(new Vector3f(0f, -1f, 0f));
        mesh.getNormals().add(new Vector3f(0f, -1f, 0f));
        mesh.getNormals().add(new Vector3f(0f, -1f, 0f));
        mesh.getNormals().add(new Vector3f(0f, -1f, 0f));
        
        mesh.getNormals().add(new Vector3f(0f, 1f, 0f));
        mesh.getNormals().add(new Vector3f(0f, 1f, 0f));
        mesh.getNormals().add(new Vector3f(0f, 1f, 0f));
        mesh.getNormals().add(new Vector3f(0f, 1f, 0f));
    }

}
