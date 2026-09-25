import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class HexagonalNum {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures \result == \java_math(n * (2 * n - 1));
      @*/
    //@ code_java_math
    public static int hexagonalNum(int n) {
        int ans = n * (2*n - 1);
        return ans;
    }
}

