
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyLists {
    
    public static HashMap<Integer, Integer> frequencyLists(int[][] list1) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] list : list1) {
            for (int i : list) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }
        return map;
    }
}

