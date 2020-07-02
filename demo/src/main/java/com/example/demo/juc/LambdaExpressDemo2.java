package com.example.demo.juc;

/**
 * 1.函数式编程
 *  拷贝中括号，写死右箭头，落地大括号
 */
public class LambdaExpressDemo2 {

    /**
     * 主线程，一切程序的入口
     */
    public static void main(String[] args) {

        //Foo foo = () -> {System.out.println("hello lambda express");};
        //foo.sayHello();

        Foo foo = (int x, int y) -> {
            System.out.println("come in add method");
            return x+y;
        };

        System.out.println(foo.add(2, 3));

        System.out.println(foo.mul(2, 3));

        System.out.println(Foo.div(6,3));

        /*
        Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("hello 12345");
            }

            @Override
            public int add(int x, int y){
                return 0;
            }
        };
        foo.sayHello();
        */

    }

}

/**
 * @FunctionalInterface 声明这是一个函数式接口
 *
 * 函数式接口，一个接口只能有一个方法
 * default ： java8后，接口中可以有多个默认实现的方法
 * static : java8后，接口中可以有多个静态方法
 */
@FunctionalInterface
interface Foo {
    //public void sayHello();

    public int add(int x, int y);

    public default int mul(int x, int y){
        return  x*y;
    }

    public static int div(int x, int y){
        return  x/y;
    }

}