import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class SeriesSum {
    
    public static int seriesSum(int number) {
        int sum = 0;
        for (int i = 1; i <= number; i++) {
            sum += i * i;
        }
        return sum;
    }
}

