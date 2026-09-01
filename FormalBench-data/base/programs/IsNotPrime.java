import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class IsNotPrime {
    
    public static Boolean isNotPrime(int n) {
        if (n == 2) {
            return false;
        }
        if (n % 2 == 0) {
            return true;
        }
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return true;
            }
        }
        return false;
    }
}

