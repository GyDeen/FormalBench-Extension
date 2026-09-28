
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaVertex {
    
    /*@
    public normal_behavior
      assignable \nothing;
      ensures \result != null && \fresh(\result) && \result.length == 2;
      ensures a != 0 ==> \result[0] == (-(double)b / (2.0 * a));
      ensures a != 0 ==> \result[1] == (((double)4.0 * a * c) - ((double)b * b)) / (4.0 * a);
      ensures a == 0 && b == 0 ==> Double.isNaN(\result[0]) && Double.isNaN(\result[1]);
      ensures a == 0 && b > 0 ==> \result[0] == Double.NEGATIVE_INFINITY;
      ensures a == 0 && b < 0 ==> \result[0] == Double.POSITIVE_INFINITY;
      ensures a == 0 && b != 0 ==> \result[1] == Double.NEGATIVE_INFINITY;
    @*/
    public static double[] parabolaVertex(int a, int b, int c) {
        double[] vertex = new double[2];
        vertex[0] = (-(double) b / (2.0 * a));
        vertex[1] = (((double) 4.0 * a * c) - ((double) b * b)) / (4.0 * a);
        return vertex;
    }
}

