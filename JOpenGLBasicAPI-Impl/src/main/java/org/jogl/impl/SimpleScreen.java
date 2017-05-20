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

import org.jogl.impl.scene.AbstractScene;
import org.jogl.impl.scene.SimpleScene;
import org.jogl.impl.shaders.SimpleShader;
import org.jogl.impl.util.objects.Triangle;


public class SimpleScreen extends AbstractScreen<SimpleScene> {

    public SimpleScreen() {
    }

    @Override
    public void init() {
        this.scene = new SimpleScene();
        // Create shader
        scene.setShader(new SimpleShader());
        // Initialize parent (with this shader)
        super.init();
        
        // Add objects
        scene.addObject(new Triangle());
    }
    
}
