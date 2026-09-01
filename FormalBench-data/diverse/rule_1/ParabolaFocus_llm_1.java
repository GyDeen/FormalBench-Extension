
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaFocus {

	public static double[] parabolaFocus(int num, int n, int d) {
		double[] ret = new double[2];
		ret[0] = (-1.0 * n / (2 * num));
		ret[1] = (4.0 * num * d - n * n + 1.0)
				/ (4.0 * num);
		return ret;
	}
}
