import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class FindParity {
    
    public static String findParity(int x) {
        if (x % 2 == 0) {
            return "Even Parity";
        } else {
            return "Odd Parity";
        }
    }
}

