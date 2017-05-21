package org.jogl.api.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

import java.util.HashSet;
import java.util.Set;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard {
    private final long windowID;

    private final Set<Integer> pressedKeys = new HashSet<>();
    private final Set<Integer> downKeys = new HashSet<>();
    private final Set<Integer> releasedKeys = new HashSet<>();

    private final GLFWKeyCallback keyCallback = new GLFWKeyCallback() {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            if (window == windowID) { // Ensure that we are receiving only our events.
                Keyboard.this.set(key, action);
            }
        }
    };
    
    public Keyboard(long windowID) {
        this.windowID = windowID;
        
        // Register callback
        glfwSetKeyCallback(windowID, keyCallback);
        
    }

    public boolean isPressed(Key key) {
        return pressedKeys.contains(key.getCode());
    }

    public boolean isDown(Key key) {
        return downKeys.contains(key.getCode());
    }

    public boolean isReleased(Key key) {
        return releasedKeys.contains(key.getCode());
    }
    
    public void set(int k, int action) {
    
        if (action == GLFW_PRESS) {
            downKeys.add(k);
            pressedKeys.add(k);
        } else if (action == GLFW_RELEASE) {
            downKeys.remove(k);
            releasedKeys.add(k);
        }
    }

    public void update() {
        pressedKeys.clear();
        releasedKeys.clear();
    }
}
