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
package org.jogl.impl.textures;

import org.jogl.api.ImageTexture;
import org.jogl.api.Image;
import org.jogl.api.TextureParameters;
import org.jogl.impl.util.TextureUtil;

/**
 *
 * @author luis
 */
public class ImageTextureImpl implements ImageTexture {

    protected final Image image;
    protected final TextureParameters parameters;
    
    public ImageTextureImpl(Image image) {
        this.image = image;
        this.parameters = null;
    }

    public ImageTextureImpl(String path, TextureParameters parameters) {
        if (path == null || path.isEmpty()) {
            image = null;
        } else {
            image = TextureUtil.loadImage(path);
        }
        
        this.parameters = parameters;
    }

    @Override
    public TextureParameters getParameters() {
        return parameters;
    }

    @Override
    public Image getImage() {
        return image;
    }

    
}
