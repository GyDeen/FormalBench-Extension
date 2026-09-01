
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaFocus {
    
    public static double[] parabolaFocus(int a, int b, int c) {
        double[] focus = new double[2];
        focus[0] = (-1.0 * b / (2 * a));
        focus[1] = (4.0 * a * c - b * b + 1.0) / (4.0 * a);
        return focus;
    }
}

