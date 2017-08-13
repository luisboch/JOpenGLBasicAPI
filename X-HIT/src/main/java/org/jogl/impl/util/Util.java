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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
    
    
    public static String readInputStream(InputStream is) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load shader", e);
        }
    }
    
    
    
    public static <E> E ou(E... values) {
        
        for (E e : values) {
            if (!empty(e)) {
                return e;
            }
        }
        
        return values[values.length-1];
    }

    public static boolean empty(Object ob) {
        if (ob == null) {
            return true;
        } else if (ob instanceof CharSequence) {
            if (ob.toString().trim().isEmpty()) {
                return true;
            }
        } else if (ob instanceof Number) {
            if (between((Number) ob, -0.0001d, 0.0001d)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean between(Number value, Number startVal, Number endVal) {
        
        if(value == null || startVal == null || endVal == null){
            return false;
        }
        
        final BigDecimal val= new BigDecimal(value.doubleValue());
        final BigDecimal start= new BigDecimal(startVal.doubleValue());
        final BigDecimal end= new BigDecimal(endVal.doubleValue());
        
        return val.compareTo(start) >=0 && val.compareTo(end) <= 0;
    }
    
    public static String format(Vector3f v) {
        final NumberFormat nf = DecimalFormat.getNumberInstance();
        final StringBuilder b = new StringBuilder();

        b.append("[");
        if (v == null) {
            b.append("null");
        } else {
            b.append(nf.format(v.x)).append(nf.format(v.y)).append(nf.format(v.z));
        }
        b.append("]");

        return b.toString();
    }

}
