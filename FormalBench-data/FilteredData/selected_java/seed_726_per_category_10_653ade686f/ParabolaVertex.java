
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaVertex {
    
    public static double[] parabolaVertex(int a, int b, int c) {
        double[] vertex = new double[2];
        vertex[0] = (-(double) b / (2.0 * a));
        vertex[1] = (((double) 4.0 * a * c) - ((double) b * b)) / (4.0 * a);
        return vertex;
    }
}

