import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTriangle {

	public static String checkTriangle(int i, int y1, int x2, int cy, int x3,
			int y) {
		double a = Math.sqrt(
				(x2 - i) * (x2 - i) + (cy - y1) * (cy - y1));
		double d = Math.sqrt(
				(x3 - x2) * (x3 - x2) + (y - cy) * (y - cy));
		double C = Math.sqrt(
				(i - x3) * (i - x3) + (y1 - y) * (y1 - y));
		if (a + d > C && a + C > d
				&& d + C > a) {
			return "Yes";
		} else {
			return "No";
		}
	}
}
