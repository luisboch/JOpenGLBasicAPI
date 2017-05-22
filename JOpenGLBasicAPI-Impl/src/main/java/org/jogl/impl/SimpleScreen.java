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
import org.jogl.api.Camera;
import org.jogl.api.input.Key;
import org.jogl.impl.scene.SimpleScene;
import org.jogl.impl.shaders.PhongShader;
import org.jogl.impl.util.Util;
import org.jogl.impl.util.objects.Cube;
import org.jogl.impl.view.PerspectiveCamera;
import org.jogl.materials.SmoothMaterial;

public class SimpleScreen extends AbstractScreen<SimpleScene> {
    private Cube object = new Cube();

    public SimpleScreen() {
    }

    @Override
    public void init() {
        this.scene = new SimpleScene();
        // Create shader
        // Initialize parent (with this shader)

        // Add objects
        
        
        scene.addObject(object.setMaterial(new SmoothMaterial(Util.convert(Color.YELLOW))));
//
//        scene.addObject(new Triangle()
//                .setPosition(new Vector3f(0.2f, -0.2f, 0f))
//                .setMaterial(new SmoothMaterial(Util.convert(Color.BLUE)))
//        );
//
//        scene.addObject(new Triangle()
//                .setPosition(new Vector3f(0.1f, -0.1f, 0f))
//                .setMaterial(new SmoothMaterial(Util.convert(Color.red)))
//        );

        scene.setShader(new PhongShader());
        final Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);

        super.init();
    }

    @Override
    public void update(float secs) {
        super.update(secs); 
        
        if (keyboard.isDown(Key.UP)) {
            scene.getCamera().moveToFront(secs);
        }
        if (keyboard.isDown(Key.DOWN)) {
            scene.getCamera().moveToRear(secs);
        }

        if (keyboard.isDown(Key.LEFT)) {
           scene.getCamera().strafeLeft(secs);
        }

        if (keyboard.isDown(Key.RIGHT)) {
            scene.getCamera().strafeRight(secs);
        }
        
        if (keyboard.isDown(Key.D)) {
            scene.getCamera().rotateRight(secs);
        }

        if (keyboard.isDown(Key.A)) {
            scene.getCamera().rotateLeft(secs);
        }
        
        
        if(keyboard.isDown(Key.U)){
            object.getTransform().rotateY(secs);
        }
        
        if(keyboard.isDown(Key.J)){
            object.getTransform().rotateY(-secs);
        }
        
        if(keyboard.isDown(Key.K)){
            object.getTransform().rotateX(secs);
        }
        
        if(keyboard.isDown(Key.H)){
            object.getTransform().rotateX(-secs);
        }
        
        if(keyboard.isDown(Key.I)){
            object.getTransform().rotateZ(secs);
        }
        if(keyboard.isDown(Key.Y)){
            object.getTransform().rotateZ(-secs);
        }
    }

}
