
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaVertex {

	public static double[] parabolaVertex(int num, int n, int d) {
		double[] result = new double[2];
		result[0] = (-(double) n / (2.0 * num));
		result[1] = (((double) 4.0 * num * d)
				- ((double) n * n)) / (4.0 * num);
		return result;
	}
}
