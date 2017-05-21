#version 330

uniform vec3 uLightDir;

uniform vec3 uAmbientLight;
uniform vec3 uDiffuseLight;
uniform vec3 uSpecularLight;

uniform vec3 uAmbientMaterial = vec3(1.0f, 1.0f, 1.0f);
uniform vec3 uDiffuseMaterial = vec3(0.7f, 0.7f, 0.7f);
uniform vec3 uSpecularMaterial = vec3(1.0f, 1.0f, 1.0f);

uniform bool uUseColor = false;

// MUST be definded when uUseColor is true
uniform vec3 uColor;

uniform float uSpecularPower = 512.0;

in vec3 vNormal;
in vec3 vViewPath;

out vec4 outColor;

void main() {
    vec3 L = normalize(uLightDir);
    vec3 N = normalize(vNormal);

    vec3 ambient = uAmbientLight * uAmbientMaterial;
    
    float diffuseIntensity = max(dot(N, -L), 0.0);
    vec3 diffuse = diffuseIntensity * uDiffuseLight * uDiffuseMaterial;
       
    //Calculo do componente especular
    float specularIntensity = 0.0;
    if (uSpecularPower > 0.0) {
            vec3 V = normalize(vViewPath);
            vec3 R = reflect(L, N);
            specularIntensity = pow(max(dot(R, V), 0.0), uSpecularPower);
    }

    vec3 specular = specularIntensity * uSpecularLight * uSpecularMaterial;
    
    vec3 color;
    if(uUseColor) {
        color = clamp(ambient + diffuse + specular + uColor , 0.0, 1.0);
    } else {
        color = clamp(ambient + diffuse + specular, 0.0, 1.0);
    }

    outColor = vec4(color, 1.0);
}