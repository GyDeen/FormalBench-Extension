import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class RecurGcd {
    
    public static int recurGcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        if (a == b) {
            return a;
        }
        return recurGcd(b, a % b);
    }
}

