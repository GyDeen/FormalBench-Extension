import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTriangle {

	public static String checkTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		int ret = (x2 - x1) * (x2 - x1);
		int tmp = (y2 - y1) * (y2 - y1);
		double a = Math.sqrt(ret + tmp);
		int i = (x3 - x2) * (x3 - x2);
		int n = (y3 - y2) * (y3 - y2);
		double b = Math.sqrt(i + n);
		int p = (x1 - x3) * (x1 - x3);
		double c = Math.sqrt(p + (y1 - y3) * (y1 - y3));
		if (a + b > c && a + c > b && b + c > a) {
			return "Yes";
		} else {
			return "No";
		}
	}
}
