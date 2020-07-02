package com.example.demo;

public class Parent {
    public static int t = parentStaticMethod2();
    {
        System.out.println("父类非静态初始化块");
    }
    static {
        System.out.println("父类静态初始化块");
    }
    public Parent(){
        System.out.println("父类的构造方法");
    }
    public static int parentStaticMethod(){
        System.out.println("父类类的静态方法");
        return 0;
    }
    private static int parentStaticMethod2() {
        System.out.println("父类的静态方法2");
        return 9;
    }
}
