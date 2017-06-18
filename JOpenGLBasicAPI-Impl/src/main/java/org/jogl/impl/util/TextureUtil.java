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
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jogl.api.Texture;
import org.jogl.api.Image;
import static org.lwjgl.stb.STBImage.stbi_load_from_memory;

/**
 *
 * @author luis
 */
public class TextureUtil {
    
    public static ByteBuffer loadResourceToBuffer(String resource) throws IOException {
        ByteBuffer buffer;
        System.out.println("Loading image from: "+ Paths.get(resource).toAbsolutePath().toString());
        Path path = Paths.get(resource);
        if (Files.isReadable(path)) {
            try (SeekableByteChannel fc = Files.newByteChannel(path)) {
                buffer = org.lwjgl.BufferUtils.createByteBuffer((int) fc.size() + 1);
                while (fc.read(buffer) != -1) {
                    //Just keep reading
                }
            }
        } else {
            throw new IllegalStateException("Can't load resource: "+resource);
        }

        buffer.flip();
        return buffer;
    }
    
    public static Image loadImage(String path){
        final Image img = new Image();
        try {
            ByteBuffer buffer = loadResourceToBuffer(path);
            IntBuffer w = org.lwjgl.BufferUtils.createIntBuffer(1);
            IntBuffer h = org.lwjgl.BufferUtils.createIntBuffer(1);
            IntBuffer c = org.lwjgl.BufferUtils.createIntBuffer(1);
    
            img.setPixels(stbi_load_from_memory(buffer, w, h, c, 0));
            
            if (img.getPixels() == null) {
                throw new RuntimeException("Failed to load image: " + path);
            }

            img.setWidth(w.get());
            img.setHeight(h.get());
            img.setChannels (c.get());
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + path, e);
        }
        
        return img;
    }
    
    

    private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = org.lwjgl.BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }

}
