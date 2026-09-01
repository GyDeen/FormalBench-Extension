
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AllUnique {
    
    public static Boolean allUnique(int[] testArray) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i : testArray) {
            if (set.contains(i)) {
                return false;
            }
            set.add(i);
        }
        return true;
    }
}

