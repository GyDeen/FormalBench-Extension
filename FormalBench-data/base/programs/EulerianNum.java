import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class EulerianNum {
    
    public static int eulerianNum(int n, int m) {
        if (m >= n || n == 0) {
            return 0;
        }
        if (m == 0) {
            return 1;
        }
        return ((n - m) * eulerianNum(n - 1, m - 1) + (m + 1) * eulerianNum(n - 1, m));
    }
}

