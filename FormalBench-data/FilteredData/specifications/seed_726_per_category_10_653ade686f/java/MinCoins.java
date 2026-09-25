
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinCoins {
    
    /*@
    public normal_behavior
      ensures \result == (x == Integer.MAX_VALUE ? x : x + 1);
    model public static pure \bigint fb_incCost(\bigint x);
    public normal_behavior
      requires a != null && 0 <= m <= a.length && 0 <= j <= m;
      requires (\forall int k; 0 <= k < m; a[k] > 0);
      ensures \result == (v == 0 ? 0 : v < 0 || j == 0 ? Integer.MAX_VALUE : a[j - 1] > v ? fb_cost(a, m, v, j - 1) :
        fb_cost(a, m, v, j - 1) <= fb_incCost(fb_cost(a, m, v - a[j - 1], m)) ? fb_cost(a, m, v, j - 1) : fb_incCost(fb_cost(a, m, v - a[j - 1], m)));
      measured_by (v > 0 ? (\bigint)v : 0) * ((\bigint)m + 1) + j;
    model public static pure \bigint fb_cost(int[] a, int m, int v, int j);

    public normal_behavior
      requires v <= 0 || m <= 0 || (coins != null && m <= coins.length && (\forall int k; 0 <= k < m; coins[k] > 0));
      assignable \nothing;
      ensures \result == (v == 0 ? 0 : v < 0 || m <= 0 ? Integer.MAX_VALUE : fb_cost(coins, m, v, m));
      measured_by v > 0 ? v : 0;
    @*/
    //@ code_java_math
    public static int minCoins(int[] coins, int m, int v) {
        if (v == 0) {
            return 0;
        }

        if (v < 0) {
            return Integer.MAX_VALUE;
        }

        int minCoins = Integer.MAX_VALUE;

        /*@
        loop_invariant 0 <= i <= (m < 0 ? 0 : m);
        loop_invariant m > 0 ==> minCoins == fb_cost(coins, m, v, i);
        loop_invariant m <= 0 ==> minCoins == Integer.MAX_VALUE;
        loop_writes i, minCoins;
        decreases m <= 0 ? 0 : (\bigint)m - i;
        @*/
        for (int i = 0; i < m; i++) {
            int coin = coins[i];
            int remainingValue = v - coin;
            if (remainingValue >= 0) {
                int totalCoins = minCoins(coins, m, remainingValue);
                if (totalCoins != Integer.MAX_VALUE) {
                    totalCoins += 1;
                }

                if (totalCoins < minCoins) {
                    minCoins = totalCoins;
                }
            }
        }

        return minCoins;
    }
}

