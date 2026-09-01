import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoOfTriangle {

	public static int noOfTriangle(int num, int m) {
		if (num < m) {
			return -1;
		} else {
			int r = 0;
			int tri = 0;
			r = ((num - m + 1) * (num - m + 2)) / 2;
			tri = ((num - 2 * m + 1)
					* (num - 2 * m + 2)) / 2;
			return r + tri;
		}
	}
}
