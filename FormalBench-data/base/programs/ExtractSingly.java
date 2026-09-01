
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractSingly {
    
    public static int[] extractSingly(int[][] testArray) {
        Set<Integer> uniqueElements = new LinkedHashSet<>();

        for (int[] array : testArray) {
            for (int element : array) {
                uniqueElements.add(element);
            }
        }

        int[] result = new int[uniqueElements.size()];
        int index = 0;
        for (int num : uniqueElements) {
            result[index++] = num;
        }

        return result;
    }
}

