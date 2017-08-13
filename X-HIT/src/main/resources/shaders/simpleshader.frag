#version 330

uniform bool uUseTexture = false;

// MUST be definded when uUseTexture is true
uniform sampler2D uTexture;

in vec2 vTexCoord;
out vec4 out_color;

void main(){   
//    out_color = vec4(1.0, 1.0, 1.0, 1.0);
    if(uUseTexture){
        out_color = texture(uTexture, vTexCoord);
    } else {
        out_color = vec4(1.0, 1.0, 1.0, 1.0);
    }
}