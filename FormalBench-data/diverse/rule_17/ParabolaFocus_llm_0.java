
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
		double edit = 4.0 * a * c - b * b + 1.0;
		double tmp = 4.0 * a;
		focus[1] = (edit) / (tmp);
		return focus;
	}
}
