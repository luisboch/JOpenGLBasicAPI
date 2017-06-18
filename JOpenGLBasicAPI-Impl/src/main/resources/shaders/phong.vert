#version 330

uniform mat4 uProjection;
uniform mat4 uView;
uniform mat4 uWorld;

uniform vec3 uCameraPosition;
uniform vec3 uPosition;
uniform mat4 uTransform;

in vec3 aVertex;
in vec3 aNormal;
in vec2 aTexCoord;

out vec3 vNormal;
out vec3 vViewPath;
out vec2 vTexCoord;

void main() {
    vec4 transf = vec4(aVertex , 1.0) * uTransform;
    //vec4 transf = vec4(aVertex , 1.0); //
    transf = vec4(transf.xyz + uPosition, 1.0);
    transf = vec4(transf.xyz, 1.0);
    vec4 worldPos = uWorld * transf;
    gl_Position =  uProjection * uView * worldPos;
    vNormal = (uWorld * (vec4(aNormal, 0.0) * uTransform)).xyz; 
    //vNormal = (uWorld * (vec4(aNormal, 0.0))).xyz;
    vViewPath = uCameraPosition - worldPos.xyz;
    vTexCoord = aTexCoord;    
}