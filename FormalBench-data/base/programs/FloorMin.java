import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class FloorMin {
    
    public static int floorMin(int a, int b, int n) {
        int x = Math.max(b - 1, n);
        return (a * x) / b;
    }
}

