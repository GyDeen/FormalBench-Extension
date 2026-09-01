
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortCounter {
    
    public static Object[][] sortCounter(HashMap<String, Integer> dict1) {
        Object[][] result = new Object[dict1.size()][2];
        int index = 0;

        for (Map.Entry<String, Integer> entry : dict1.entrySet()) {
            result[index][0] = entry.getKey();
            result[index][1] = entry.getValue();
            index++;
        }

        Arrays.sort(result, (a, b) -> ((Integer) b[1]).compareTo((Integer) a[1]));
        
        return result;
    }
}

