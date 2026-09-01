import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CountUnsetBits {
    
    public static int countUnsetBits(int n) {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            int temp = i;
            while (temp != 0) {
                if ((temp & 1) == 0) {
                    cnt++;
                }
                temp = temp >> 1;
            }
        }
        return cnt;
    }
}

