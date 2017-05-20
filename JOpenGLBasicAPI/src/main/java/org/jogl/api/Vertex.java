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
package org.jogl.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.joml.Vector3f;

/**
 *
 * @author luis
 */
public class Vertex implements List<Vector3f> {

    private final Vector3f[] vectors;

    public Vertex() {
        this.vectors = new Vector3f[]{new Vector3f(), new Vector3f(), new Vector3f()};
    }

    public Vertex(Vector3f... vectors) {
        
        if (vectors == null) {
            this.vectors = new Vector3f[]{new Vector3f(), new Vector3f(), new Vector3f()};
        } else if (vectors.length != 3) {
            throw new IllegalArgumentException("Vertex receives only 3 vectors");
        } else {
            this.vectors = vectors;
        }

    }

    @Override
    public int size() {
        return vectors.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return Arrays.asList(vectors).contains(o);
    }

    @Override
    public Iterator<Vector3f> iterator() {
        return Arrays.asList(vectors).iterator();
    }

    @Override
    public Object[] toArray() {
        return vectors;
    }

    @Override
    public <T> T[] toArray(T[] a) {

        for (int i = 0; i < a.length && i < vectors.length; i++) {
            a[i] = (T) vectors[i];
        }

        return a;
    }

    @Override
    public boolean add(Vector3f e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return Arrays.asList(vectors).containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Vector3f> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int index, Collection<? extends Vector3f> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector3f get(int index) {

        if (index < vectors.length) {
            return vectors[index];
        }

        return null;
    }

    @Override
    public Vector3f set(int index, Vector3f element) {

        if (index < vectors.length) {
            vectors[index] = element;
        }

        return element;
    }

    @Override
    public void add(int index, Vector3f element) {
        set(index, element);
    }

    @Override
    public Vector3f remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(Object o) {
        return Arrays.asList(vectors).indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return Arrays.asList(vectors).indexOf(o);
    }

    @Override
    public ListIterator<Vector3f> listIterator() {
        return Arrays.asList(vectors).listIterator();
    }

    @Override
    public ListIterator<Vector3f> listIterator(int index) {
        return Arrays.asList(vectors).listIterator(index);
    }

    @Override
    public List<Vector3f> subList(int fromIndex, int toIndex) {
        return Arrays.asList(vectors).subList(fromIndex, toIndex);
    }
}
