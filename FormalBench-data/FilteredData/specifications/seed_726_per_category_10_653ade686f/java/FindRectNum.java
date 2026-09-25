import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class FindRectNum {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures \result == \java_math(n * (n + 1));
      @*/
    //@ code_java_math
    public static int findRectNum(int n) {
        return n*(n + 1);
    }
}

