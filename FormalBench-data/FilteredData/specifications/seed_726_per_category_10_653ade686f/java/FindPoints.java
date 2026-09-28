
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindPoints {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures \result != null && \fresh(\result) && \result.length == 2;
      @ ensures l1 < l2 && r1 < r2 ==> \result[0] == (l1 < r1 ? l1 : r1) && \result[1] == (r2 > l2 ? r2 : l2);
      @ ensures l1 > l2 && r1 > r2 ==> \result[0] == (l2 < r2 ? l2 : r2) && \result[1] == (l1 > r1 ? l1 : r1);
      @ ensures !(l1 < l2 && r1 < r2) && !(l1 > l2 && r1 > r2) ==> \result[0] == l1 && \result[1] == r1;
      @*/
    //@ code_java_math
    public static int[] findPoints(int l1, int r1, int l2, int r2) {
        int[] res = new int[2]; // Array to store the result points
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

