package com.rtinform;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaCounter {
    public static Map<Integer, Integer> arrayCounter(int[] intArray) {
        Map<Integer, Integer> assArray = Arrays.stream(intArray)
            .boxed()
            .collect(Collectors.groupingBy(e -> e, Collectors.summingInt(e -> 1)));
        return assArray;
    }
}
