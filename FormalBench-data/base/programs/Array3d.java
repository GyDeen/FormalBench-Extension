
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Array3d {
    
    public static String[][][] array3d(int m, int n, int o) {
        String[][][] arr3d = new String[o][n][m]; 
        
        for (int i = 0; i < o; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    arr3d[i][j][k] = "*";
                }
            }
        }
        
        return arr3d;
    }
}

