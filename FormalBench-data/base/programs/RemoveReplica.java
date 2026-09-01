
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveReplica {
    
    public static Object[] removeReplica(int[] testTup) {
        int n = testTup.length;
        Object[] result = new Object[n];
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            if (!set.contains(testTup[i])) {
                result[i] = testTup[i];
                set.add(testTup[i]);
            } else {
                result[i] = "MSP";
            }
        }
        
        return result;
    }
}

