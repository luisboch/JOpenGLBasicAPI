#version 330

uniform vec3 aColor;

out vec4 out_color;

void main(){
     out_color = vec4(aColor, 1.0);
}