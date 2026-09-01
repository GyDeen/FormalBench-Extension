import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class Check {
    
    public static Boolean check(int n) {
        int temp = n;
        int count = 0;
        while (temp > 0) {
            int temp2 = temp % 10;
            count += temp2 * temp2;
            temp = temp / 10;
        }
        return n == 0 || count % 2 == 0;
    }
}

