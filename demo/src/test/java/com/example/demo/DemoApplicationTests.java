package com.example.demo;

import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        System.out.println(CollectionUtils.isNotEmpty(list));

        List list2 = null;
        System.out.println(CollectionUtils.isNotEmpty(list2));

        System.out.println(123);

    }

}
