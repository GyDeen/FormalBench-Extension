import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class FindIndex {
    
    public static int findIndex(int n) {
        int x = (int) Math.round(Math.sqrt(2 * Math.pow(10, (n - 1))));
        return x;
    }
}

