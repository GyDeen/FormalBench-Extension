
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaFocus {

	public static double[] parabolaFocus(int num, int n, int d) {
		double[] result = new double[2];
		result[0] = (-1.0 * n / (2 * num));
		result[1] = (4.0 * num * d - n * n + 1.0)
				/ (4.0 * num);
		return result;
	}
}
