import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TriangleArea {

	public static int triangleArea(int n) {
		int i = 0;
		if (n < 0) {
			return -1;
		} else if (n == 0) {
			i = 0;
		} else {
			i = (int) Math.sqrt(3) * n * n;
		}
		return i;
	}
}
