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
import org.jogl.materials.SmoothMaterial;
import org.jphysics.api.Effect;
import org.jphysics.math.Vector3f;


public class ExplosionEffect extends TimeOutObject implements Effect{

    public ExplosionEffect(float secs) {
        super(secs);
        setMaterial(new SmoothMaterial(Util.convert(Color.YELLOW)));
        scale(new Vector3f(0.03f));
        setMaxVelocity(10f);
    }
    
}
