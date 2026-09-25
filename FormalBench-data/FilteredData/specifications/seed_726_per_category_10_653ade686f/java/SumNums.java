import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class SumNums {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures \result == (m <= \java_math(x + y) && \java_math(x + y) <= n ? 20 : \java_math(x + y));
      @*/
    //@ code_java_math
    public static int sumNums(int x, int y, int m, int n) {
        int sum = x + y;
        if (sum >= m && sum <= n) {
            return 20;
        } else {
            return sum;
        }
    }
}

