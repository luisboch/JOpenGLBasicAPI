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
package org.jogl.api.input;

import java.util.HashMap;
import java.util.Map;
import static org.lwjgl.glfw.GLFW.*;

/**
 *
 * @author luis
 */
public enum Key {

    ESCAPE(GLFW_KEY_ESCAPE),
    N_0(GLFW_KEY_0),
    N_1(GLFW_KEY_1),
    N_2(GLFW_KEY_2),
    N_3(GLFW_KEY_3),
    N_4(GLFW_KEY_4),
    N_5(GLFW_KEY_5),
    N_6(GLFW_KEY_6),
    N_7(GLFW_KEY_7),
    N_8(GLFW_KEY_8),
    N_9(GLFW_KEY_9),
    MINUS(GLFW_KEY_MINUS),
    EQUALS(GLFW_KEY_EQUAL),
    BACK(GLFW_KEY_BACKSPACE),
    TAB(GLFW_KEY_TAB),
    Q(GLFW_KEY_Q),
    W(GLFW_KEY_W),
    E(GLFW_KEY_E),
    R(GLFW_KEY_R),
    T(GLFW_KEY_T),
    Y(GLFW_KEY_Y),
    U(GLFW_KEY_U),
    I(GLFW_KEY_I),
    O(GLFW_KEY_O),
    P(GLFW_KEY_P),
    LBRACKET(GLFW_KEY_LEFT_BRACKET),
    RBRACKET(GLFW_KEY_RIGHT_BRACKET),
    RETURN(GLFW_KEY_ENTER),
    LCONTROL(GLFW_KEY_LEFT_CONTROL),
    A(GLFW_KEY_A),
    S(GLFW_KEY_S),
    D(GLFW_KEY_D),
    F(GLFW_KEY_F),
    G(GLFW_KEY_G),
    H(GLFW_KEY_H),
    J(GLFW_KEY_J),
    K(GLFW_KEY_K),
    L(GLFW_KEY_L),
    SEMICOLON(GLFW_KEY_SEMICOLON),
    APOSTROPHE(GLFW_KEY_APOSTROPHE),
    GRAVE(GLFW_KEY_GRAVE_ACCENT),
    LSHIFT(GLFW_KEY_LEFT_SHIFT),
    BACKSLASH(GLFW_KEY_BACKSLASH),
    Z(GLFW_KEY_Z),
    X(GLFW_KEY_X),
    C(GLFW_KEY_C),
    V(GLFW_KEY_V),
    B(GLFW_KEY_B),
    N(GLFW_KEY_N),
    M(GLFW_KEY_M),
    COMMA(GLFW_KEY_COMMA),
    PERIOD(GLFW_KEY_PERIOD),
    SLASH(GLFW_KEY_SLASH),
    RSHIFT(GLFW_KEY_RIGHT_SHIFT),
    MULTIPLY(GLFW_KEY_KP_MULTIPLY),
    LMENU(GLFW_KEY_MENU),
    SPACE(GLFW_KEY_SPACE),
    CAPITAL(GLFW_KEY_CAPS_LOCK),
    NUMLOCK(69),
    SCROLL(70),
    NUMPAD7(71),
    NUMPAD8(72),
    NUMPAD9(73),
    SUBTRACT(74),
    NUMPAD4(75),
    NUMPAD5(76),
    NUMPAD6(77),
    ADD(78),
    NUMPAD1(79),
    NUMPAD2(80),
    NUMPAD3(81),
    NUMPAD0(82),
    DECIMAL(83),
    F1(GLFW_KEY_F1),
    F2(GLFW_KEY_F2),
    F3(GLFW_KEY_F3),
    F4(GLFW_KEY_F4),
    F5(GLFW_KEY_F5),
    F6(GLFW_KEY_F6),
    F7(GLFW_KEY_F7),
    F8(GLFW_KEY_F8),
    F9(GLFW_KEY_F9),
    F10(GLFW_KEY_F10),
    F11(GLFW_KEY_F11),
    F12(GLFW_KEY_F12),
    F13(GLFW_KEY_F13),
    F14(GLFW_KEY_F14),
    F15(GLFW_KEY_F15),
    F16(GLFW_KEY_F16),
    F17(GLFW_KEY_F17),
    F18(GLFW_KEY_F18),
    NUMPADENTER(GLFW_KEY_KP_ENTER),
    RCONTROL(GLFW_KEY_RIGHT_CONTROL),
    NUMPADCOMMA(GLFW_KEY_KP_DECIMAL),
    DIVIDE(GLFW_KEY_KP_DIVIDE),
    RMENU(GLFW_KEY_MENU),
    HOME(GLFW_KEY_HOME),
    UP(GLFW_KEY_UP),
    LEFT(GLFW_KEY_LEFT),
    RIGHT(GLFW_KEY_RIGHT),
    END(GLFW_KEY_END),
    DOWN(GLFW_KEY_DOWN),
    INSERT(GLFW_KEY_INSERT),
    DELETE(GLFW_KEY_DELETE),
    LMETA(GLFW_KEY_LEFT_SUPER),
    LWIN(GLFW_KEY_LEFT_SUPER),
    RMETA(GLFW_KEY_RIGHT_SUPER),
    RWIN(GLFW_KEY_RIGHT_SUPER);

    private final int code;


    Key(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
