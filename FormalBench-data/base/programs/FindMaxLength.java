
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMaxLength {
    
    public static int findMaxLength(int[][] lst) {
        int maxLength = 0;
        for (int i = 0; i < lst.length; i++) {
            int tempLength = 0;
            for (int j = 0; j < lst[i].length; j++) {
                if (lst[i][j] == 0) {
                    tempLength = 0;
                    break;
                } else {
                    tempLength++;
                }
            }
            if (tempLength > maxLength) {
                maxLength = tempLength;
            }
        }
        return maxLength;
    }
}

