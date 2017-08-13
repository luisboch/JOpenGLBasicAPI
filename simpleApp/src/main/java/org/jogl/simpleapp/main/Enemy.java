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
import org.jogl.impl.util.Util;
import org.jogl.impl.util.objects.Cube;
import org.jogl.materials.SmoothMaterial;
import org.jphysics.math.Vector3f;

/**
 *
 * @author luis
 */
public class Enemy extends Cube {

    private int life;

    public Enemy() {
        super();
        life = 5;
        setMaterial(new SmoothMaterial(Util.convert(Color.RED)));
        scale(new Vector3f(1.2f));
        setMaxVelocity(10f);
        setRadius(1f);

    }

    public void setLife(int life) {
        this.life = life;
    }

    /**
     *
     * @return
     */
    @Override
    public Enemy decreaseLife() {
        life--;
        return this;
    }

    @Override
    public boolean isAlive() {
        return life > 0;
    }

}
