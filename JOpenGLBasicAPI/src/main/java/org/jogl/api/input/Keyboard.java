package org.jogl.api.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

import java.util.HashSet;
import java.util.Set;

public class Keyboard  {
    
    private static final Keyboard INSTANCE = new Keyboard();
    
    private final Set<Integer> pressedKeys = new HashSet<>();
    private final Set<Integer> downKeys = new HashSet<>();
    private final Set<Integer> releasedKeys = new HashSet<>();
        
    private Keyboard() {        
    }
    
    public static Keyboard getInstance() {
        return INSTANCE;
    }
    
    public boolean isPressed(int key) {
        return pressedKeys.contains(key);
    }
    
    public boolean isDown(int key) {
        return downKeys.contains(key);
    }
    
    public boolean isReleased(int key) {
        return releasedKeys.contains(key);
    }
    
    public void set(int key, int action) {
        if (action == GLFW_PRESS) {
            downKeys.add(key);
            pressedKeys.add(key);
        }
        else if (action == GLFW_RELEASE) {
            downKeys.remove(key);
            releasedKeys.add(key);
        }
    }
    
    public void update() {
        pressedKeys.clear();
        releasedKeys.clear();
    }
}
