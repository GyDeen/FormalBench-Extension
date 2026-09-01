import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TriangleArea {

	public static int triangleArea(int r) {
		int area = 0;
		if (r < 0) {
			return -1;
		} else
			area = (r == 0) ? 0 : (int) Math.sqrt(3) * r * r;
		return area;
	}
}
