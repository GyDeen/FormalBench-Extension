
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaVertex {

	public static double[] parabolaVertex(int a, int b, int c) {
		double[] vertex = new double[2];
		double Vector = 2.0 * a;
		vertex[0] = (-(double) b / (Vector));
		double tmp = ((double) 4.0 * a * c) - ((double) b * b);
		double max = 4.0 * a;
		vertex[1] = (tmp) / (max);
		return vertex;
	}
}
