import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class DealnnoyNum {
    
    public static int dealnnoyNum(int n, int m) {
        if (m == 0 || n == 0) {
            return 1;
        }
        return dealnnoyNum(m - 1, n) + dealnnoyNum(m - 1, n - 1) + dealnnoyNum(m, n - 1);
    }
}

