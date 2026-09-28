import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class DiameterCircle {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures \result == \java_math(2 * r);
      @*/
    //@ code_java_math
    public static int diameterCircle(int r) {
        return 2 * r;
    }
}

