import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TriangleArea {

	public static int triangleArea(int num) {
		int triangle = 0;
		if (num < 0) {
			return -1;
		} else if (num == 0) {
			triangle = 0;
		} else {
			triangle = (int) Math.sqrt(3) * num * num;
		}
		return triangle;
	}
}
