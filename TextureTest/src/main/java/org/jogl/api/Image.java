package org.jogl.api;

import java.nio.ByteBuffer;

public class Image {
    
    private ByteBuffer pixels;

    private int width;
    private int height;
    private int channels;


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getChannels() {
        return channels;
    }

    public ByteBuffer getPixels() {
        return pixels.asReadOnlyBuffer();
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPixels(ByteBuffer pixels) {
        this.pixels = pixels;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
