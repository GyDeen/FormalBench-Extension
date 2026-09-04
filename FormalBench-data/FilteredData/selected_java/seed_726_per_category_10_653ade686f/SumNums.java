import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class SumNums {
    
    public static int sumNums(int x, int y, int m, int n) {
        int sum = x + y;
        if (sum >= m && sum <= n) {
            return 20;
        } else {
            return sum;
        }
    }
}

