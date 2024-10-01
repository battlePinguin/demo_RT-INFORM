package com.rtinform

class GroovyCounter {
    Map<Integer, Integer> countArray(int[]inputArray) {
        return inputArray.toList().countBy{ it }
    }
}
