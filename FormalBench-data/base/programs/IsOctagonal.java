import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class IsOctagonal {
    
    public static int isOctagonal(int n) {
        int [] f = new int[n + 1];
        f[0] = 0;
        for (int i = 0; i <= n - 1; i++) {
            f[i + 1] = f[i] + 6 * i + 1;
        }
        return f[n];
    }
}

