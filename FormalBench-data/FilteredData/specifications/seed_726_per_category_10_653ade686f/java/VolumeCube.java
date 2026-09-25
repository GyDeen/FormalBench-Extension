import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class VolumeCube {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures \result == \java_math(l * l * l);
      @*/
    //@ code_java_math
    public static int volumeCube(int l) {
        return l * l * l;
    }
}

