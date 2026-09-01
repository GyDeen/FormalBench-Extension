
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaFocus {

	public static double[] parabolaFocus(int p, int n, int d) {
		double[] result = new double[2];
		result[0] = (-1.0 * n / (2 * p));
		result[1] = (4.0 * p * d - n * n + 1.0)
				/ (4.0 * p);
		return result;
	}
}
