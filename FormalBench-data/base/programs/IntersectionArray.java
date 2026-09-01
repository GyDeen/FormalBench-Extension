
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IntersectionArray {
    
    public static int[] intersectionArray(int[] arrayNums1, int[] arrayNums2) {
        Set<Integer> set = new HashSet<>();
        List<Integer> intersection = new ArrayList<>();

        for (int num : arrayNums2) {
            set.add(num);
        }

        for (int num : arrayNums1) {
            if (set.contains(num)) {
                intersection.add(num);
            }
        }

        return intersection.stream().mapToInt(i -> i).toArray();
    }
}

