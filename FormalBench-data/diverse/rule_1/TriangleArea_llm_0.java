import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TriangleArea {

	public static int triangleArea(int n) {
		int triangle = 0;
		if (n < 0) {
			return -1;
		} else if (n == 0) {
			triangle = 0;
		} else {
			triangle = (int) Math.sqrt(3) * n * n;
		}
		return triangle;
	}
}
