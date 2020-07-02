package com.example.demo.list;

import java.util.Arrays;

public class MyArrayList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elements;

    public MyArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public boolean add(T element) {
        //elements[size++] = o;
        add(size, element);
        return true;
    }

    public boolean add(int index, T element) {
        if (index < 0 || index > size) {
            return false;
        }

        ensureCapacity();

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        size++;
        return true;
    }

    public void ensureCapacity() {
        if (size < elements.length) {
            return;
        }
        int newCapacity = elements.length + (elements.length >> 1); //1.5
        T[] newElements = (T[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            return false;
        }

        Object old = elements[index];
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return true;
    }

    public boolean set(int index, T element) {
        if (index < 0 || index >= size) {
            return false;
        }

        elements[index] = element;
        return true;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "size=" + size +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(9 >> 1);

    }


}
