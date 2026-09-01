
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveTuple {
    
    public static int[] removeTuple(int[] testTup) {
        Set<Integer> set = new HashSet<>();
        for (int number : testTup) {
            set.add(number);
        }
        int[] result = new int[set.size()];
        int index = 0;
        for (Integer num : set) {
            result[index++] = num;
        }
        Arrays.sort(result);
        return result;
    }
}

