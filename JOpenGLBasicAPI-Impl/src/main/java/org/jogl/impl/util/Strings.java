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

import java.util.List;

/**
 *
 * @author luis
 */
public class Strings {
    
    public static final String EMPTY = "";
    
    public static String join(List<String> strings, String separator){
        
        if(strings == null){
            return EMPTY;
        }
        
        
        final StringBuilder b = new StringBuilder();
        strings.forEach((str) -> {
            b.append(str).append(separator);
        });
        
        return b.toString();
    }
    
    public static boolean empty(String str){
        return str == null || str.trim().isEmpty();
    }
}
