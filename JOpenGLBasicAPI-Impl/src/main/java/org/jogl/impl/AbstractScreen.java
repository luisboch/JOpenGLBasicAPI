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

import org.jogl.api.Camera;
import org.jogl.api.Scene;
import org.jogl.api.input.Keyboard;
import org.jogl.api.input.events.Mouse;
import org.jogl.api.screen.Screen;

/**
 *
 * @author luis
 * @param <S>
 */
public abstract class AbstractScreen<S extends Scene> implements Screen {
    
    protected S scene;
    
    protected Mouse mouse;
    protected Keyboard keyboard;

    @Override
    public void init() {
        scene.init();
    }

    @Override
    public void update(float secs) {
        scene.update(secs);
    }

    @Override
    public void draw() {
        scene.render();
    }

    @Override
    public void deinit() {
        scene.deiInit();
    }
    
    @Override
    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    @Override
    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

}
