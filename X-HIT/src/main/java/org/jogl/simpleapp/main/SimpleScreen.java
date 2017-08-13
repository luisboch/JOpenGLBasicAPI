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

import java.awt.Color;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.jogl.api.Config;
import org.jogl.api.Object3D;
import org.jogl.api.input.Key;
import org.jogl.api.screen.Scene;
import org.jogl.impl.AbstractScene;
import org.jogl.impl.shaders.PhongShader;
import org.jogl.impl.util.Util;
import org.jogl.impl.util.objects.AbstractObject;
import org.jogl.impl.util.objects.Cube;
import org.jogl.impl.util.objects.Square;
import org.jogl.materials.SmoothMaterial;
import org.jphysics.DestroyListener;
import org.jphysics.Engine;
import org.jphysics.api.ContactListener;
import org.jphysics.api.GameObject;
import org.jphysics.api.PhysicObject;
import org.jphysics.math.Vector3f;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.glfwGetCurrentContext;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

public class SimpleScreen extends AbstractScene implements DestroyListener, ContactListener, Weapon.Resolver {

    private static final float MOVE_MULTIPLIER = 4f;
    private static final float WEAPON_RELOAD = 1f;
    private static final float WEAPON_EXPLOSION_TIME = 0.2f;
    private static final float WEAPON_EXPLOSION_VELOCITY = 2f;
    private float timeoutToCreateEnemy = 4f;

    private static final String WEAPON_NAME = "_FIRE_";
    private static final String PLAYER_NAME = "_PLAYER_";
    private Cube control = new Cube();
    private Cube top = new Cube();
    private Cube left = new Cube();
    private Cube rigth = new Cube();

    private Square square1 = new Square();
    private Square square2 = new Square();
    private Weapon weapon;

    final Engine engine = new Engine(10, 10, 10);

    int level = 1;
    long lastEnemyCreated;

    private int points = 0;

    public SimpleScreen() {
    }

    @Override
    public Scene init() {
        Config.defaultView();
        super.init();
        super.setShader(new PhongShader());

        //Configuring the area.
        control.setMaterial(new SmoothMaterial(Util.convert(Color.BLUE)));
        control.scale(new Vector3f(2f, 0.5f, 0.5f));
        control.setPosition(new Vector3f(engine.getHeight() * 0.5f, engine.getHeight() * 0.5f, 0));
        control.setName(PLAYER_NAME);
        addObject(control);
        weapon = new Weapon(control, this, WEAPON_RELOAD, this);

        // Configuring the space
        top.setPosition(control.getPosition().add(new Vector3f(0, 0, -15)));
//        top.scale(new Vector3f(10));
        top.setMaterial(new SmoothMaterial(Util.convert(Color.red)));
        addObject(top);

        // Configuing camera view
        getCamera().setPosition(control.getPosition().add(new Vector3f(0f, 4.4f, 9.6f)));
        getCamera().setDirection(new Vector3f(0f, -0.308f, -1.976f));

        engine.setContactListener(this);
        engine.setDestroyListener(this);
        engine.setDestroyOnLeaveMap(true);
        engine.setAvoidOjectsLeaveMap(true);

        lastEnemyCreated = System.currentTimeMillis();
        return this;
    }

    @Override
    protected void createObject(Object3D object3D) {
        super.createObject(object3D);
        engine.add(object3D);
    }

    @Override
    public Scene update(float secs) {

        final Object3D object = control;
//        
        super.update(secs);
        engine.calculate(secs);
        if (keyboard.isDown(Key.U)) {
            getCamera().moveToFront(secs);
        }
        if (keyboard.isDown(Key.J)) {
            getCamera().moveToRear(secs);
        }

        if (keyboard.isDown(Key.H)) {
            getCamera().strafeLeft(secs);
        }

        if (keyboard.isDown(Key.K)) {
            getCamera().strafeRight(secs);
        }

        if (keyboard.isDown(Key.I)) {
            getCamera().rotateRight(secs);
        }

        if (keyboard.isDown(Key.Y)) {
            getCamera().rotateLeft(secs);
        }
        if (keyboard.isDown(Key.O)) {
            getCamera().lookUP(secs);
        }
        if (keyboard.isDown(Key.L)) {
            getCamera().lookDown(secs);
        }

        if (keyboard.isDown(Key.A) || keyboard.isDown(Key.LEFT)) {
            final Vector3f pos = object.getPosition();
            pos.x -= secs * MOVE_MULTIPLIER;
            object.setPosition(pos);
        }
        if (keyboard.isDown(Key.D) || keyboard.isDown(Key.RIGHT)) {
            final Vector3f pos = object.getPosition();
            pos.x += secs * MOVE_MULTIPLIER;
            object.setPosition(pos);
        }

        if (keyboard.isDown(Key.T)) {
            object.getTransform().rotateZ(-secs);
        }
        if (keyboard.isDown(Key.G)) {
            object.getTransform().rotateY(-secs);
        }
        if (keyboard.isDown(Key.SPACE)) {
            weapon.fire();

        }
        weapon.update(secs);

        checkForEnemy();

        return this;
    }

    /**
     * Called by engine when the object die and will be removed from scene.
     *
     * @param obj
     */
    @Override
    public void destroy(GameObject obj) {
        if (isFire(obj)) {
            final Random r = new Random();
            // Just for better view
            final float maxVel = WEAPON_EXPLOSION_VELOCITY;
            for (int i = 0; i < 7; i++) {
                final TimeOutObject t = new ExplosionEffect(WEAPON_EXPLOSION_TIME);
                t.setPosition(obj.getPosition());
                float x = r.nextFloat() * (r.nextBoolean() ? -maxVel : maxVel);
                float y = r.nextFloat() * (r.nextBoolean() ? -maxVel : maxVel);
                float z = r.nextFloat() * (r.nextBoolean() ? -maxVel : maxVel);
                t.setVelocity(new Vector3f(x, y, z));
                addObject(t);
            }
        } else if (obj instanceof Enemy) {
            level++;
            System.out.println("Pontuação: " + points);
        }

        removeObject((Object3D) obj);
    }

    /**
     * Called from weapon when new projectile will be fired.
     *
     * @return
     */
    @Override
    public Object3D create() {
        Cube cube = new Cube();
        cube.setMaterial(new SmoothMaterial(Util.convert(Color.YELLOW)));
        cube.setMaxVelocity(15f);

        cube.scale(new Vector3f(0.05f, 0.05f, 0.1f));
        cube.setName(WEAPON_NAME);
        return cube;
    }

    private boolean isFire(GameObject obj) {
        return obj instanceof AbstractObject && Objects.equals(((AbstractObject) obj).getName(), WEAPON_NAME);
    }

    private boolean isPlayer(GameObject obj) {
        return obj instanceof AbstractObject && Objects.equals(((AbstractObject) obj).getName(), PLAYER_NAME);
    }

    private void checkForEnemy() {
        final List<Enemy> enemies = engine.getActorsByType(Enemy.class);
        final long now = System.currentTimeMillis();
        if (enemies.size() < level && ((now - lastEnemyCreated) / 1000) > timeoutToCreateEnemy) {

            Enemy enemy = new Enemy();
            final Random rnd = new Random();
            float nextFloat = rnd.nextFloat() * 5f * (rnd.nextBoolean() ? -1 : 1);
            float nextVel = rnd.nextFloat() * 3f;
            Vector3f pos = control.getPosition().add(new Vector3f(nextFloat, 0, -10f));
////            pos.y = 0;
//            pos.x += nextFloat;
//            pos.y += -10f;

            enemy.setPosition(pos);
            enemy.setVelocity(new Vector3f(0, 0, 0.1f * level * nextVel));
            timeoutToCreateEnemy *= 0.5f;
            addObject(enemy);
            lastEnemyCreated = now;
            return;
        }

        for (Enemy e : enemies) {
            if (e.getPosition().z >= control.getPosition().z) {
                exit();
            }
        }

    }

    /**
     * Called from engine when the object hits another.
     *
     * @param obj1
     * @param obj2
     * @param deadObjects
     */
    @Override
    public void contact(PhysicObject obj1, PhysicObject obj2, List<PhysicObject> deadObjects) {
        boolean enemy = (obj1 instanceof Enemy || obj2 instanceof Enemy);
        boolean fire = isFire(obj1) || isFire(obj2);
        boolean player = isPlayer(obj1) || isPlayer(obj2);

        if (enemy && fire) {
            if (obj1 instanceof Enemy) {
                obj1.decreaseLife();
                engine.remove(obj2);
            } else {
                obj2.decreaseLife();
                engine.remove(obj1);
            }
        } else if (enemy && player) {
            exit();
        }
    }

    public void exit() {
        System.out.println("GAME OVER: pontuação: " + points);
        glfwSetWindowShouldClose(glfwGetCurrentContext(), GLFW_TRUE);
    }

}
