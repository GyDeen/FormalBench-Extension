import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class MaxOfTwo {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures \result == (x > y ? x : y);
      @*/
    //@ code_java_math
    public static int maxOfTwo(int x, int y) {
        return x > y ? x : y;
    }
}

