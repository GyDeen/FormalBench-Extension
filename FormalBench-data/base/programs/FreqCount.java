
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FreqCount {
    
    public static HashMap<Integer, Integer> freqCount(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], map.getOrDefault(array[i], 0) + 1);
        }
        return map;
    }
}

