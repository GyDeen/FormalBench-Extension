
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Maxaverageofpath {
    
    public static Double maxaverageofpath(int[][] cost, int n) {
        double[][] dp = new double[n][n];
        
        dp[0][0] = cost[0][0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + cost[i][0];
        }

        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + cost[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + cost[i][j];
            }
        }
        
        return dp[n - 1][n - 1] / (2 * n - 1);
    }
}

