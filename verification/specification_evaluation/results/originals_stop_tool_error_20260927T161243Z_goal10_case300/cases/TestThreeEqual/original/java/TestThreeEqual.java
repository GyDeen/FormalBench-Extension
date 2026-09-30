import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class TestThreeEqual {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures \result == (x == y && y == z ? 3 : (x == y || y == z || x == z ? 2 : 0));
      @*/
    //@ code_java_math
    public static int testThreeEqual(int x, int y, int z) {
        if(x == y && y == z)
            return 3;
        else if(x == y || y == z || x == z)
            return 2;
        else
            return 0;
    }
}

