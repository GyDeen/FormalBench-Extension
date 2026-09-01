import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class HexagonalNum {
    
    public static int hexagonalNum(int n) {
        int ans = n * (2*n - 1);
        return ans;
    }
}

