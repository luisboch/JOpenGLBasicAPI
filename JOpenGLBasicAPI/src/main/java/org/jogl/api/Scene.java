/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jogl.api;

/**
 *
 * @author luis
 */
public interface Scene {

    void init();

    void update(float secs);

    void draw();

    void deinit();

}
