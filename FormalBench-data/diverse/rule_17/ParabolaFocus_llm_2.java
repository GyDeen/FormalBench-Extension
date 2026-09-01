
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaFocus {

	public static double[] parabolaFocus(int a, int b, int c) {
		double[] focus = new double[2];
		double result = -1.0 * b;
		int i = 2 * a;
		focus[0] = (result / (i));
		double tmp = 4.0 * a * c - b * b + 1.0;
		double out = 4.0 * a;
		focus[1] = (tmp) / (out);
		return focus;
	}
}
