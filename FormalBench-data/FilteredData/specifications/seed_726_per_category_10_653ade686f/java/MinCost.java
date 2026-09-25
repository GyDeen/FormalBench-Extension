
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class MinCost {
    
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ ensures \result == (a <= b ? a : b);
      @ model public static pure \bigint fb_min(\bigint a, \bigint b);
      @*/
    /*@ public normal_behavior
      @ requires a != null && 0 <= r < a.length && c >= 0;
      @ requires (\forall int k; 0 <= k <= r; a[k] != null && c < a[k].length);
      @ ensures \result == (r == 0 && c == 0 ? a[0][0] : r == 0 ? fb_wrap(fb_cost(a, 0, c - 1) + a[0][c]) : c == 0 ? fb_wrap(fb_cost(a, r - 1, 0) + a[r][0]) : fb_wrap(fb_min(fb_cost(a, r - 1, c - 1), fb_min(fb_cost(a, r - 1, c), fb_cost(a, r, c - 1))) + a[r][c]));
      @ measured_by (\bigint)r + c;
      @ model public static pure \bigint fb_cost(int[][] a, int r, int c);
      @*/
    /*@ public normal_behavior
      @ requires cost != null && 0 <= m < cost.length && n >= 0;
      @ requires (\forall int k; 0 <= k <= m; cost[k] != null && n < cost[k].length);
      @ assignable \nothing;
      @ ensures \result == fb_cost(cost, m, n);
      @*/
    //@ code_java_math
    public static int minCost(int[][] cost, int m, int n) {
        int[][] tc = new int[m + 1][n + 1];
        tc[0][0] = cost[0][0];
        /*@ loop_invariant 1 <= i <= m + 1;
          @ loop_invariant (\forall int r; 0 <= r < i; tc[r][0] == fb_cost(cost, r, 0));
          @ loop_writes i, tc[*][0];
          @ decreases (\bigint)m - i + 1;
          @*/
        for (int i = 1; i <= m; i++) {
            tc[i][0] = tc[i - 1][0] + cost[i][0];
        }
        /*@ loop_invariant 1 <= j <= n + 1;
          @ loop_invariant (\forall int c; 0 <= c < j; tc[0][c] == fb_cost(cost, 0, c));
          @ loop_writes j, tc[0][*];
          @ decreases (\bigint)n - j + 1;
          @*/
        for (int j = 1; j <= n; j++) {
            tc[0][j] = tc[0][j - 1] + cost[0][j];
        }
        /*@ loop_invariant 1 <= i <= m + 1;
          @ loop_invariant (\forall int r, c; 0 <= r < i && 0 <= c <= n; tc[r][c] == fb_cost(cost, r, c));
          @ loop_invariant (\forall int r; i <= r <= m; tc[r][0] == fb_cost(cost, r, 0));
          @ loop_writes i, tc[*][*];
          @ decreases (\bigint)m - i + 1;
          @*/
        for (int i = 1; i <= m; i++) {
            /*@ loop_invariant 1 <= j <= n + 1;
              @ loop_invariant (\forall int c; 0 <= c < j; tc[i][c] == fb_cost(cost, i, c));
              @ loop_writes j, tc[i][*];
              @ decreases (\bigint)n - j + 1;
              @*/
            for (int j = 1; j <= n; j++) {
                tc[i][j] = Math.min(tc[i - 1][j - 1], Math.min(tc[i - 1][j], tc[i][j - 1])) + cost[i][j];
            }
        }
        return tc[m][n];
    }
}

