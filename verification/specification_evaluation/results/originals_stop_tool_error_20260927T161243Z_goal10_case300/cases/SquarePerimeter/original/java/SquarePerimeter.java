import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class SquarePerimeter {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures \result == \java_math(4 * a);
      @*/
    //@ code_java_math
    public static int squarePerimeter(int a) {
        return 4 * a;
    }
}

