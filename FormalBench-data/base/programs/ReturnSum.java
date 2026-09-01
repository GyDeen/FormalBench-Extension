import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class ReturnSum {
    
    public static int returnSum(HashMap<String, Integer> dict) {
        int sum = 0;
        for (Map.Entry<String, Integer> entry : dict.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }
}

