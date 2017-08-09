package org.jogl.simpleapp.main;

import java.awt.Color;
import org.jogl.api.Object3D;
import org.jogl.impl.AbstractScene;
import org.jogl.impl.util.Util;
import org.jogl.impl.util.objects.Cube;
import org.jogl.materials.SmoothMaterial;
import org.jphysics.api.GameObject;
import org.jphysics.math.Vector3f;

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
/**
 *
 * @author luis
 */
public class Weapon {

    private final GameObject from;
    private final Resolver resolver;
    private final float reloadTime;

    private float lastfire = 0f;

    private final AbstractScene scene;

    public Weapon(GameObject from, Resolver resolver, float reloadTime, AbstractScene scene) {
        this.from = from;
        this.resolver = resolver;
        this.reloadTime = reloadTime;
        this.scene = scene;

    }

    public GameObject getFrom() {
        return from;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public void update(float secs) {
        lastfire += secs;
    }

    public void fire() {
        if (lastfire >= reloadTime) {
            final Object3D ob = resolver.create();
            ob.setVelocity(new Vector3f(0f, 0f, -5f));
            ob.setPosition(from.getPosition());
            lastfire = 0f;
            scene.addObject(ob);
        }
    }

    public static interface Resolver {

        Object3D create();
    }

}