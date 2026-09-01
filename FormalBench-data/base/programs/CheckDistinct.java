
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckDistinct {
    
    public static Boolean checkDistinct(int[] testTup) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < testTup.length; i++) {
            if (set.contains(testTup[i])) {
                return false;
            } else {
                set.add(testTup[i]);
            }
        }
        return true;
    }
}

