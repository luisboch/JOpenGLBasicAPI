/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jogl.api;

import java.util.List;

/**
 *
 * @author luis
 */
public interface Scene {

    Scene init();

    /**
     * default shader
     *
     * @return self
     */
    Shader getShader();

    List<GlobalLight> getLights();

    List<Filter> getFilters();

    List<Object3D> getObjects();

    Scene update(float secs);

    Scene render();

    Scene deiInit();

    Scene addObject(Object3D object3D);

    Scene removeObject(Object3D object3D);

    Scene addLight(GlobalLight light);

    Scene removeLight(GlobalLight light);

    public static class MeshReference {

        public final Mesh mesh;
        public final Object3D object;
        public final int meshId;
        public final ArrayBuffer array;

        public MeshReference(Mesh mesh, Object3D object, int meshId, ArrayBuffer array) {
            this.mesh = mesh;
            this.object = object;
            this.meshId = meshId;
            this.array = array;
        }
    }

    public static class ArrayBuffer {
        public final int id;
        public final int elementSize;
        public final int elementCount;

        public ArrayBuffer(int id, int elementSize, int elementCount) {
            this.id = id;
            this.elementSize = elementSize;
            this.elementCount = elementCount;
        }
        
    }
}
