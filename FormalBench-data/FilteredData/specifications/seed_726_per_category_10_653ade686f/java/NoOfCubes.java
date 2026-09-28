import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class NoOfCubes {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures \result == \java_math((n - k + 1) * (n - k + 1) * (n - k + 1));
      @*/
    //@ code_java_math
    public static int noOfCubes(int n, int k) {
        int no = 0;
        no = (n - k + 1) * (n - k + 1) * (n - k + 1);
        return no;
    }
}

