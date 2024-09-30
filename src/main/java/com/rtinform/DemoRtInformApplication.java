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
    public void run(String... args) throws Exception {
        int[] exampleArray = {1, 3, 4, 5, 1, 5, 4};
        //Java
        Map<Integer, Integer> javaResult = JavaCounter.arrayCounter(exampleArray);
        System.out.println( Arrays.toString(exampleArray) + " j-> " + javaResult);
        // Groovy
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        Class<?> groovyClass = groovyClassLoader.parseClass(new File("src/main/java/com/rtinform/GroovyCounter.groovy"));

        Object groovyObject = groovyClass.newInstance();

        Map<Integer, Integer> result = (Map<Integer, Integer>) groovyClass
            .getMethod("countOccurrences", int[].class)
            .invoke(groovyObject, (Object) exampleArray);

        System.out.println( Arrays.toString(exampleArray) + " g-> " + result);
    }
}
