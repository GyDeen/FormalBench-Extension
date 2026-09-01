import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TriangleArea {

	public static int triangleArea(int r) {
		int area = 0;
		if (r < 0) {
			return -1;
		} else if (0 == r) {
			area = 0;
		} else {
			area = (int) Math.sqrt(3) * r * r;
		}
		return area;
	}
}
