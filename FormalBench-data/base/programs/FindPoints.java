
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindPoints {
    
    public static int[] findPoints(int l1, int r1, int l2, int r2) {
        int[] res = new int[2]; 
        if (l1 < l2 && r1 < r2) {
            res[0] = Math.min(l1, r1);
            res[1] = Math.max(r2, l2);
        } else if (l1 > l2 && r1 > r2) {
            res[0] = Math.min(l2, r2);
            res[1] = Math.max(l1, r1);
        } else {
            res[0] = l1;
            res[1] = r1;
        }
        return res;
    }
}

