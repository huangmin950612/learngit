package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void println() {
        System.out.println(123);
    }

    public static void main(String[] args) {
        List list = new ArrayList<>();
        System.out.println(CollectionUtils.isEmpty(list));

        List list2 = null;
        System.out.println(CollectionUtils.isEmpty(list2));
    }

    @Test
    public void println2() {
        System.out.println(1234);
    }


    @Test
    public void println3() {
        System.out.println(12345);
    }

}
