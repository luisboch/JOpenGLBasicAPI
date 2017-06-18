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
package org.jogl.impl;

import java.awt.Color;
import org.jogl.api.Config;
import org.jogl.api.Object3D;
import org.jogl.api.TextureParameters;
import org.jogl.api.input.Key;
import org.jogl.impl.scene.SimpleScene;
import org.jogl.impl.shaders.PhongShader;
import org.jogl.impl.textures.ImageTextureImpl;
import org.jogl.impl.util.Util;
import org.jogl.impl.util.objects.Cube;
import org.jogl.impl.util.objects.Square;
import org.jogl.impl.util.objects.Triangle;
import org.jogl.impl.view.PerspectiveCamera;
import org.jogl.materials.SmoothMaterial;
import org.joml.Vector3f;

public class SimpleScreen extends AbstractScreen<SimpleScene> {

    private Cube cube = new Cube();
    private Triangle triangle = new Triangle();
    private Square square1 = new Square();
    private Square square2 = new Square();

    public SimpleScreen() {
    }

    @Override
    public void init() {

        Config.defaultView();

        this.scene = new SimpleScene();

        scene.addObject(cube.setMaterial(new SmoothMaterial(Util.convert(Color.YELLOW)))
                .setPosition(new Vector3f(-1.2f, 0f, -5f))
        );
//
//        scene.addObject(triangle
//                .setPosition(new Vector3f(.8f, -0.2f, 0f))
//        );
//
//        scene.addObject(square1.setMaterial(
//                new SmoothMaterial(
//                        new Vector3f(1f, 1f, 1f), // ambient 
//                        new Vector3f(1f, 0.2f, 1f), // difuse
//                        new Vector3f(1f, 1f, 1f), // specular material
//                        300f, // power
//                        Util.convert(Color.BLUE), // color
//                        null)
////                        .setTexture(new ImageTextureImpl("textures/bricks_t.jpg", new TextureParameters()))
//        ).setPosition(new Vector3f(-0.5f, -0.5f, -1f)
//        ));
////        
        scene.addObject(square2.setMaterial(
                new SmoothMaterial(Util.convert(Color.GREEN))
                        .setTexture(new ImageTextureImpl("textures/bricks_t.jpg", new TextureParameters()))
        ).setPosition(new Vector3f(1f, -1f, -01f)
        ));

        scene.setShader(new PhongShader());
        scene.setCamera(new PerspectiveCamera().moveToRear(3));

        super.init();
    }

    @Override
    public void update(float secs) {

        final Object3D object = square2;
        super.update(secs);

        if (keyboard.isDown(Key.W)) {
            scene.getCamera().moveToFront(secs);
        }
        if (keyboard.isDown(Key.S)) {
            scene.getCamera().moveToRear(secs);
        }

        if (keyboard.isDown(Key.A)) {
            scene.getCamera().strafeLeft(secs);
        }

        if (keyboard.isDown(Key.D)) {
            scene.getCamera().strafeRight(secs);
        }

        if (keyboard.isDown(Key.E)) {
            scene.getCamera().rotateRight(secs);
        }

        if (keyboard.isDown(Key.Q)) {
            scene.getCamera().rotateLeft(secs);
        }

        if (keyboard.isDown(Key.U)) {
            object.getTransform().rotateY(secs);
        }

        if (keyboard.isDown(Key.J)) {
            object.getTransform().rotateY(-secs);
        }

        if (keyboard.isDown(Key.K)) {
            object.getTransform().rotateX(secs);
        }

        if (keyboard.isDown(Key.H)) {
            object.getTransform().rotateX(-secs);
        }

        if (keyboard.isDown(Key.I)) {
            object.getTransform().rotateZ(secs);
        }
        if (keyboard.isDown(Key.Y)) {
            object.getTransform().rotateZ(-secs);
        }
    }

}
