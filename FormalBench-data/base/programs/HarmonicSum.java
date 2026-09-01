import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class HarmonicSum {
    
    public static Double harmonicSum(int n) {
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0 / i;
        }
        return sum;
    }
}

