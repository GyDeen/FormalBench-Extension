import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class NewmanPrime {
    
    public static int newmanPrime(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return 2 * newmanPrime(n - 1) + newmanPrime(n - 2);
    }
}

