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
package org.jogl.api.input.events;

/**
 *
 * @author luis
 */
public enum MouseButton {
    
    MOUSE_LEFT(0),
    MOUSE_RIGTH(1),
    MOUSE_MIDDLE(2),
    MOUSE_0(0),
    MOUSE_1(1),
    MOUSE_2(2),
    MOUSE_3(3),
    MOUSE_4(4),
    MOUSE_5(5),
    MOUSE_6(6),
    MOUSE_7(7);
    
    private final int code;

    private MouseButton(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    
    public static MouseButton from(int code){
        for (MouseButton m:values()) {
            if(m.getCode() == code){
                return m;
            }
        }
        
        return null;
    }
    
    public boolean is(int code){
        return this.code == code;
    }
    
}
