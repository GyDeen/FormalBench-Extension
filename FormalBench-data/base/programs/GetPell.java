import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class GetPell {
    
    public static int getPell(int n) {
        int [] pell = new int[n + 1];
        pell[0] = 0;
        pell[1] = 1;
        for (int i = 2; i <= n; i++) {
            pell[i] = (2 * pell[i - 1] + pell[i - 2]) % 1000000000;
        }
        return pell[n];
    }
}

