#version 330

in vec3 aVertex;
in vec2 aTexCoord;

out vec2 vTexCoord;

void main(){
     gl_Position = vec4(aVertex, 1.0);
     vTexCoord = aTexCoord;   
}