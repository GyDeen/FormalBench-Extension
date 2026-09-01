import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTriangle {

	public static String checkTriangle(int i, int y1, int x2, int y, int x3,
			int x) {
		double a = Math.sqrt(
				(x2 - i) * (x2 - i) + (y - y1) * (y - y1));
		double d = Math.sqrt(
				(x3 - x2) * (x3 - x2) + (x - y) * (x - y));
		double C = Math.sqrt(
				(i - x3) * (i - x3) + (y1 - x) * (y1 - x));
		if (a + d > C && a + C > d
				&& d + C > a) {
			return "Yes";
		} else {
			return "No";
		}
	}
}
