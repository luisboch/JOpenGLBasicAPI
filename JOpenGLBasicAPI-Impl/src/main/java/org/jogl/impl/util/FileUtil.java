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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class FileUtil {

    public static String readFile(String file) {
        return readFile(new File(file));
    }

    public static String readFile(File file) {
        if (file.exists() && file.canRead()) {
            try {
                return Strings.join(Files.readAllLines(file.toPath()), "\n");
            } catch (IOException ex) {
                Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
                return "";
            }
        }

        return Strings.EMPTY;
    }
    
    
    /**
     * Returns path that can reference some files in jar.
     * @return 
     */
    public String getReferencePath(){
        return getClass().getClassLoader().getResource(".").getPath();
    }

}
