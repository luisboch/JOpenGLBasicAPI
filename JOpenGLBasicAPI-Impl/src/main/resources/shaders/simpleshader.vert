#version 330

uniform vec3 aPosition;

in vec3 aVertex;

void main(){
     gl_Position = vec4(aVertex+aPosition, 1.0);
}