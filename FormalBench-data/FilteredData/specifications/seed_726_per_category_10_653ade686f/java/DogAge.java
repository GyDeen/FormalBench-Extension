import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class DogAge {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ ensures \result == (hAge >= 0 ? \java_math((hAge - 2) * 4 + 21) : \java_math((hAge + 2) * 4 + 21));
      @*/
    //@ code_java_math
    public static int dogAge(int hAge) {
        int dogYears;
        if (hAge >= 0) {
            dogYears = (hAge - 2) * 4 + 21;
        } else {
            dogYears = (hAge + 2) * 4 + 21;
        }
        return dogYears;
    }
}

