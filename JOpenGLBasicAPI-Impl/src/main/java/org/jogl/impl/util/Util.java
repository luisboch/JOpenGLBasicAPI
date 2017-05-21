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
package org.jogl.impl.util;

import org.joml.Vector3f;

/**
 *
 * @author luis
 */
public class Util {
    
    public static final Vector3f colorFromRGB(int r, int g, int b){
        return new Vector3f(r/255, g/255, b/255);
    }
    
    public static final Vector3f convert(java.awt.Color c){
        return colorFromRGB(c.getRed(), c.getGreen(), c.getBlue());
    }
    
    public static boolean isSubClass(Class clazzSub, Class clazzSuper ){
        
        if(clazzSub ==  null || clazzSuper ==  null){
            return false;
        }
        
        return clazzSuper.isAssignableFrom(clazzSub);
    }
}
