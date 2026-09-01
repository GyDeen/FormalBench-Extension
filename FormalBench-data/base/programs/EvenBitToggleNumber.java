import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class EvenBitToggleNumber {
    
    public static int evenBitToggleNumber(int n) {
        int res = 0;
        int count = 0;
        int temp = n;
        while (temp > 0) {
            if (count % 2 == 0) {
                res = res | (1 << count);
            }
            count++;
            temp >>= 1;
        }
        return n ^ res;
    }
}

