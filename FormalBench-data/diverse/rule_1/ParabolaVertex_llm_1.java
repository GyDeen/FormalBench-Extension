
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaVertex {

	public static double[] parabolaVertex(int num, int n, int d) {
		double[] ret = new double[2];
		ret[0] = (-(double) n / (2.0 * num));
		ret[1] = (((double) 4.0 * num * d)
				- ((double) n * n)) / (4.0 * num);
		return ret;
	}
}
