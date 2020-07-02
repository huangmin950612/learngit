package com.example.demo.jvm;

import java.util.ArrayList;
import java.util.List;

public class MyObject {

    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();

        System.out.println("maxMemory:" + maxMemory / (double) 1024 / 1024 + "MB");
        System.out.println("totalMemory:" + totalMemory / (double) 1024 / 1024 + "MB");


        List list = new ArrayList<>();
        list.add(null);
        System.out.println(list.get(0));

    }

}
