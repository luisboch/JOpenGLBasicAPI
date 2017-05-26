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

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengles.GLES20.*;

/**
 *
 * @author luis
 */
public class Config {
    
    public static boolean cullFace = true;
    public static boolean depthTest = true;
    public static final List<RenderMode> modes = new ArrayList<>();
    
    
    /**
     * start default configurations
     */
    static{
        defaultView();
    }
    
    public static enum PoligonMode {
        
        FRONT(GL_FRONT),
        FRONT_AND_BACK(GL_FRONT_AND_BACK),
        BACK(GL_BACK);

        private final int code;

        private PoligonMode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
        
    }
    
    public static enum PoligonType {
        
        FILL(GL11.GL_FILL),
        LINE(GL_LINES),
        POINT(GL_POINTS);

        private final int code;

        private PoligonType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
        
    }
    
    
    public static class RenderMode {

        private final PoligonType type;
        private final PoligonMode mode;

        private RenderMode(PoligonMode mode, PoligonType type) {
            this.type = type;
            this.mode = mode;
        }

        public PoligonMode getMode() {
            return mode;
        }

        public PoligonType getType() {
            return type;
        }
        
    }
    
    
    public static void lineView(){
        cullFace = false;
        depthTest = false;
        modes.add(new RenderMode(PoligonMode.FRONT_AND_BACK, PoligonType.LINE));
    }
    
    public static void pointView(){
        cullFace = false;
        depthTest = false;
        modes.add(new RenderMode(PoligonMode.FRONT_AND_BACK, PoligonType.POINT));
    }
    
    public static void debugView(){
        cullFace = false;
        depthTest = false;
        modes.clear();
        modes.add(new RenderMode(PoligonMode.BACK, PoligonType.LINE));
        modes.add(new RenderMode(PoligonMode.FRONT, PoligonType.FILL));
    }
    
    private static void defaultView() {
        cullFace = true;
        modes.clear();
        modes.add(new RenderMode(PoligonMode.FRONT_AND_BACK, PoligonType.FILL));
    }
    
}
