import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class ParallelogramPerimeter {
    
    public static int parallelogramPerimeter(int b, int h) {
        if (b <= 0 || h <= 0) {
            return 0;
        }
        return 2 * (b * h);
    }
}

