import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class IsPerfectSquare {
    
    public static Boolean isPerfectSquare(int n) {
        if (n < 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }
}

