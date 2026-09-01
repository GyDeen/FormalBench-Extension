
import java.io.*;
import java.lang.*;
import java.math.*;

class MinVal {
    
    public static int minVal(Object[] arrayval) {
        int min = Integer.MAX_VALUE;
        for (Object val : arrayval) {
            if (val instanceof Integer) {
                min = Math.min(min, (Integer) val);
            }
        }
        return min;
    }
}

