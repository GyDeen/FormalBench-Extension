import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class JacobsthalNum {
    
    public static int jacobsthalNum(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 2 * dp[i - 2];
        }
        return dp[n];
    }
}

