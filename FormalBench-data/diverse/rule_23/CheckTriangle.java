import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTriangle {

	public static String checkTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		double a = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		double b = Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2));
		double c = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
		if (c < a + b && b < a + c && a < b + c) {
			return "Yes";
		} else {
			return "No";
		}
	}
}
