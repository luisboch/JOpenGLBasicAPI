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
package org.jogl.api.screen;

import org.jogl.api.input.Keyboard;
import org.jogl.api.input.events.Mouse;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

public class Window {

    // The window handle
    private long windowID;
    private Screen screen;
    private int width;
    private int height;
    private String title;
    private Mouse mouse;
    private Keyboard keyboard;

    private GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.out);
    private GLFWWindowSizeCallback resizeCallback = GLFWWindowSizeCallback.create(new GLFWWindowSizeCallback.SAM() {
        @Override
         public void invoke(long window, int w, int h) {
            if (window == windowID) {
                width = w;
                height = h;
            }
        }
    });
    
    public Window(Screen screen, String title, int width, int height) {
        this.screen = screen;
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public Window(Screen screen, String title) {
        this(screen, title, 800, 600);
    }

    public Window(Screen screen) {
        this(screen, "Game");
    }

    private void init() {
        System.setProperty("java.awt.headless", "true");
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback);

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (glfwInit() == GLFW_FALSE) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure our window
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden
                                                  // after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be
                                                   // resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        // Create the window
        windowID = glfwCreateWindow(width, height, title, NULL, NULL);
        if (windowID == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Setup a key callback. It will be called every time a key is pressed,
        // repeated or released.
        mouse = new Mouse(windowID);
        keyboard = new Keyboard(windowID);
       
        screen.setMouse(mouse);
        screen.setKeyboard(keyboard);
        
        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(windowID, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);

        // Make the OpenGL context current
        glfwMakeContextCurrent(windowID);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(windowID);
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GLCapabilities capabilities = GL.createCapabilities();

        // Set the clear color
        screen.init();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        long before = System.currentTimeMillis() - 1;
        while (glfwWindowShouldClose(windowID) == GLFW_FALSE) {
            float time = (System.currentTimeMillis() - before) / 1000f;
            before = System.currentTimeMillis() - 1;
            screen.update(time);
            screen.draw();

            keyboard.update();
            mouse.update();
            
            glfwSwapBuffers(windowID);
            glfwPollEvents();
        }
        
        screen.deinit();
    }

    public void show() {
        try {
            init();
            loop();

            // Destroy window and window callbacks
            glfwDestroyWindow(windowID);
        } finally {
            // Terminate GLFW and free the GLFWErrorCallback
            glfwTerminate();
        }
    }

}
