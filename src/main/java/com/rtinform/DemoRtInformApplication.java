package com.rtinform;

import groovy.lang.GroovyClassLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
public class DemoRtInformApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoRtInformApplication.class, args);
    }

    @Override
    public void run(String... args) {
        int[] exampleArray = {1, 3, 4, 5, 1, 5, 4};

        // Java
        Map<Integer, Integer> javaResult = JavaCounter.arrayCounter(exampleArray);
        System.out.println(Arrays.toString(exampleArray) + " j-> " + javaResult);

        // Groovy
        Class<?> groovyClass;
        try (GroovyClassLoader groovyClassLoader = new GroovyClassLoader()) {

            try {
                groovyClass = groovyClassLoader.parseClass(new File("src/main/java/com/rtinform/GroovyCounter.groovy"));
                Object groovyObject = groovyClass.getDeclaredConstructor().newInstance();

                Map<Integer, Integer> result = (Map<Integer, Integer>) groovyClass
                    .getMethod("countArray", int[].class)
                    .invoke(groovyObject, exampleArray);
                System.out.println(Arrays.toString(exampleArray) + " g-> " + result);
            } catch (Exception e) {
                System.err.println("Произошла ошибка при работе с Groovy-классом: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("Ошибка при работе с GroovyClassLoader: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
