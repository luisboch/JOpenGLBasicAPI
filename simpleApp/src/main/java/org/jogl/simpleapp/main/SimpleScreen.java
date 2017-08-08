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
package org.jogl.simpleapp.main;

import java.awt.Color;
import org.jogl.api.Config;
import org.jogl.api.Object3D;
import org.jogl.api.input.Key;
import org.jogl.api.screen.Scene;
import org.jogl.impl.AbstractScene;
import org.jogl.impl.shaders.PhongShader;
import org.jogl.impl.util.Util;
import org.jogl.impl.util.objects.Cube;
import org.jogl.impl.util.objects.Square;
import org.jogl.materials.SmoothMaterial;
import org.jphysics.Engine;
import org.jphysics.math.Vector3f;

public class SimpleScreen extends AbstractScene {
    
    private static final float MOVE_MULTIPLIER = 4f;
    private static final float WEAPON_RELOAD = 1f;
    
    private Cube control = new Cube();
    private Square square1 = new Square();
    private Square square2 = new Square();
    
    private float lastfire = 0f;
    
    final Engine engine = new Engine(10000, 10000);
    
    public SimpleScreen() {
    }
    
    @Override
    public Scene init() {
        Config.defaultView();
        super.init();
        super.setShader(new PhongShader());
        
        control.setMaterial(new SmoothMaterial(Util.convert(Color.BLUE)));
        addObject(control);
        
        getCamera().setPosition(new Vector3f(0f, 4.4f, 9.6f));
        getCamera().setDirection(new Vector3f(0f, -0.308f, -1.976f));
        
        return this;
    }
    
    @Override
    protected void createObject(Object3D object3D) {
        super.createObject(object3D);
        engine.add(object3D);
    }
    
    @Override
    public Scene update(float secs) {
        
        final Object3D object = control;
//        
        super.update(secs);
        engine.calculate(secs);
        if (keyboard.isDown(Key.U)) {
            getCamera().moveToFront(secs);
        }
        if (keyboard.isDown(Key.J)) {
            getCamera().moveToRear(secs);
        }
        
        if (keyboard.isDown(Key.H)) {
            getCamera().strafeLeft(secs);
        }
        
        if (keyboard.isDown(Key.K)) {
            getCamera().strafeRight(secs);
        }
        
        if (keyboard.isDown(Key.I)) {
            getCamera().rotateRight(secs);
        }
        
        if (keyboard.isDown(Key.Y)) {
            getCamera().rotateLeft(secs);
        }
        if (keyboard.isDown(Key.O)) {
            getCamera().lookUP(secs);
        }
        if (keyboard.isDown(Key.L)) {
            getCamera().lookDown(secs);
        }

//        System.out.println("Camera pos:" + getCamera().getPosition());
//        System.out.println("Camera dir:" + getCamera().getDirection());
        if (keyboard.isDown(Key.A) || keyboard.isDown(Key.LEFT)) {
            final Vector3f pos = object.getPosition();
            pos.x -= secs * MOVE_MULTIPLIER;
            object.setPosition(pos);
        }
        if (keyboard.isDown(Key.D) || keyboard.isDown(Key.RIGHT)) {
            final Vector3f pos = object.getPosition();
            pos.x += secs * MOVE_MULTIPLIER;
            object.setPosition(pos);
        }
        
        if (keyboard.isDown(Key.T)) {
            object.getTransform().rotateZ(-secs);
        }
        if (keyboard.isDown(Key.G)) {
            object.getTransform().rotateY(-secs);
        }
        if (keyboard.isDown(Key.SPACE)) {
            fire(secs);
        }
        
        lastfire += secs;
        
        return this;
    }
    
    public void fire(float secs) {
        if (lastfire >= WEAPON_RELOAD) {
            Cube cube = new Cube();
            cube.setMaterial(new SmoothMaterial(Util.convert(Color.YELLOW)));
            Vector3f pos = control.getPosition();
            cube.setPosition(pos);
            cube.scale(new Vector3f(0.05f, 0.05f, 0.1f));
            cube.setVelocity(new Vector3f(0f, 0f, -5f));
            cube.setMaxVelocity(15f);
            addObject(cube);
            lastfire = 0f;
        }
    }
    
}
