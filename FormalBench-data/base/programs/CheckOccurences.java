
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckOccurences {
    
    public static HashMap<String, Integer> checkOccurences(int[][] testArray) {
        HashMap<String, Integer> result = new HashMap<>();
        
        for (int[] tuple : testArray) {
            Arrays.sort(tuple);
            String key = Arrays.toString(tuple);
            result.put(key, result.getOrDefault(key, 0) + 1);
        }
        
        return result;
    }
}

