
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaVertex {

	public static double[] parabolaVertex(int p, int n, int d) {
		double[] result = new double[2];
		result[0] = (-(double) n / (2.0 * p));
		result[1] = (((double) 4.0 * p * d)
				- ((double) n * n)) / (4.0 * p);
		return result;
	}
}
