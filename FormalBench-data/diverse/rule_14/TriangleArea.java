import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TriangleArea {

	public static int triangleArea(int r) {
		int area = 0;
		if (!(r < 0)) {
			if (r == 0) {
				area = 0;
			} else {
				area = (int) Math.sqrt(3) * r * r;
			}
		} else {
			return -1;
		}
		return area;
	}
}
