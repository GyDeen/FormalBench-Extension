
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxVal {
    
    public static int maxVal(Object[] arrayval) {
        int max = 0;
        for (Object val : arrayval) {
            if (val instanceof Integer) {
                max = Math.max((Integer) val, max);
            }
        }
        return max;
    }
}

