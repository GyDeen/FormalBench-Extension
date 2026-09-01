import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class FifthPowerSum {
    
    public static int fifthPowerSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += (int) Math.pow(i, 5);
        }
        return sum;
    }
}

