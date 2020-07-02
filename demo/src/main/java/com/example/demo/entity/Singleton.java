package com.example.demo.entity;

public class Singleton {
    private Singleton(){}
    private static class Instance{
        private static Singleton singleton = new Singleton();
    }
    public static Singleton getInstance(){
        return Instance.singleton;
    }
}


