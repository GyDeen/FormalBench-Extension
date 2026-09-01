import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class IsNonagonal {
    
    public static int isNonagonal(int n) {
        int [] f = new int[n + 1];
        f[0] = 0;
        for (int i = 0; i <= n - 1; i++) {
            f[i + 1] = f[i] + 7 * i + 1;
        }
        return f[n];
    }
}

