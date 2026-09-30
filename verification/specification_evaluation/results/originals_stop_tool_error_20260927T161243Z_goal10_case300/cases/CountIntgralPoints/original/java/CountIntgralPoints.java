import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CountIntgralPoints {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures \result == \java_math((y2 - y1 - 1) * (x2 - x1 - 1));
      @*/
    //@ code_java_math
    public static int countIntgralPoints(int x1, int y1, int x2, int y2) {
        return ((y2 - y1 - 1) * (x2 - x1 - 1));
    }
}

