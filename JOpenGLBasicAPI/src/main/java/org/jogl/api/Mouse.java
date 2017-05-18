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
package org.jogl.api;

import java.awt.event.MouseEvent;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

/**
 *
 * @author luis
 */
public class Mouse {
    
    private Listener listener = new MouseAdapter();
    
    private GLFWMouseButtonCallback buttonCallBack = new GLFWMouseButtonCallback() {
        @Override
        public void invoke(long window, int button, int action, int mods) {
//            switch(action){
//                case 1:
//            }
        }
    };
    
    private GLFWCursorPosCallback posCallback = new GLFWCursorPosCallback() {
        @Override
        public void invoke(long window, double xpos, double ypos) {
            pos.x = (float) xpos;
            pos.y = (float) ypos;
        }
    };
    
    private Vector2f pos = new Vector2f();
    
    private static Mouse instance;

    public Mouse() {
    }
    
    
    public static synchronized Mouse getInstance() {
        if(instance == null){
            instance = new Mouse();
        }
        return instance;
    }

    public GLFWMouseButtonCallback getButtonCallBack() {
        return buttonCallBack;
    }

    public GLFWCursorPosCallback getPosCallback() {
        return posCallback;
    }

    public Vector2f getPos() {
        return pos;
    }
    
    private static interface Listener{
        void mousePress(MouseButton m, MouseEvent e);
        void mouseRelease(MouseButton m, MouseEvent e);
    }
    
    private static class MouseAdapter implements Listener{
        @Override
        public void mousePress(MouseButton m, MouseEvent e) {
        }
        @Override
        public void mouseRelease(MouseButton m, MouseEvent e) {
        }
        
    }
    
}
