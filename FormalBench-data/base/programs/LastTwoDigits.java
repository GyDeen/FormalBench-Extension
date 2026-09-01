import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class LastTwoDigits {
    
    public static int lastTwoDigits(int n) {
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        int lastTwoDigits = (int) (factorial % 100);
        return lastTwoDigits;
    }
}

