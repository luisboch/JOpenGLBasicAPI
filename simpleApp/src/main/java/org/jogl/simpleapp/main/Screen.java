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
package org.jogl.simpleapp.main;

import org.jogl.api.Scene;
import org.jogl.api.Window;
import org.joml.AxisAngle4f;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

/**
 *
 * @author luis
 */
public class Screen implements Scene {
//
//    private Mesh mesh;
//    private OpenGLManager manager;
    private float angle;
    private final int horizontal;
    private final int vertical;

    public Screen(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

//    @Override
    public void init() {
//        manager = new OpenGLManager();
//
//        final MeshCreator meshCreator = new MeshCreator().loadShader("basic");
//
//        // montamos uma linha da esfera, fazendo triangulos a partir do meio,
//        // depois e so girar 180 graus, teremos o lado oposto
//        // Vamos usar raio de 0.5f
//        float tamRaio = 0.5f;
//        
//        Vector3f ponta = new Vector3f(0f, tamRaio, 0f);
//        Vector3f angBase = new Vector3f(0f, 0f, 0f);
//        
//        int metadeHoriz = (int) ((float) horizontal *0.5f);
//        float valorParcialAngulo  = (int) (180f / ((float)metadeHoriz) );
//        Matrix3f rotacao = new Matrix3f().rotateY(valorParcialAngulo).rotateX(valorParcialAngulo);
//        for(int y=0; y<vertical;y++){
//            
//            for (int i = 0; i < metadeHoriz; i++) {
//                meshCreator.from(angBase);
//                meshCreator.to(ponta);
//                final Quaternionf rotation = new Quaternionf().setFromNormalized(rotacao);
//                meshCreator.to(ponta.normalize().rotate(rotation).mul(tamRaio));
////                meshCreator.to(ponta.normalize().rotate((float)Math.toRadians(valorParcialAngulo)).mul(tamRaio));
//            }
////            rotacao.rotateZ(valorParcialAngulo);
////            
////            meshCreator.copyTo(-1f, 1f, 1f);
//            
//        }
//        
//        
////        System.out.println(meshCreator.toString());
//        mesh = meshCreator.create();
//        manager.add(mesh);

    }

    @Override
    public void update(float secs) {
//        Vector2f pos = mouse.getPos();
//        System.out.println("Mouse.pos: (" +pos.x+", "+pos.y+")");
        angle += secs * 100 ;
    }

    @Override
    public void draw() {

//        float angle = (float) Math.toRadians(this.angle);
//        mesh.setUniform("uWorld", new Matrix4f().rotateY(-angle).rotateX(-angle).rotateZ(-angle));
//        manager.draw();
    }

    @Override
    public void deinit() {
    }

    public static void main(String[] args) {
        new Window(new Scene() {
            @Override
            public void init() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void update(float secs) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void draw() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deinit() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }).show();

    }
}
