import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class ParallelogramPerimeter {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures \result == (b <= 0 || h <= 0 ? 0 : \java_math(2 * b * h));
      @*/
    //@ code_java_math
    public static int parallelogramPerimeter(int b, int h) {
        if (b <= 0 || h <= 0) {
            return 0;
        }
        return 2 * (b * h);
    }
}

