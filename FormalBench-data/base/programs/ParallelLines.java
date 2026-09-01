
import java.io.*;
import java.lang.*;
import java.math.*;

class ParallelLines {
    
    public static Boolean parallelLines(int[] line1, int[] line2) {
        int x1 = line1[0];
        int y1 = line1[1];
        int x2 = line2[0];
        int y2 = line2[1];
        return (x1 * y2 == x2 * y1);
    }
}

