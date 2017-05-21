#version 330

uniform mat4 uProjection;
uniform mat4 uView;
uniform mat4 uWorld;

uniform vec3 uCameraPosition;
uniform vec3 uPosition;
uniform mat4 uTransform;

in vec3 aVertex;
in vec3 aNormal;

out vec3 vNormal;
out vec3 vViewPath;

void main() {
    vec4 worldPos = uWorld * vec4(aVertex + uPosition, 1.0) * uTransform;
    gl_Position =  uProjection * uView * worldPos;
    vNormal = (uWorld * vec4(aNormal, 0.0)).xyz;
    vViewPath = uCameraPosition - worldPos.xyz;    
}