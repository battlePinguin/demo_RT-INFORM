package com.rtinform

class GroovyCounter {
    Map<Integer, Integer> countOccurrences(int[]inputArray) {
        return inputArray.toList().countBy{ it }
    }
}
