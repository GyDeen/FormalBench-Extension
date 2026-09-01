import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoOfTriangle {

	public static int noOfTriangle(int num, int p) {
		if (num < p) {
			return -1;
		} else {
			int r = 0;
			int tri = 0;
			r = ((num - p + 1) * (num - p + 2)) / 2;
			tri = ((num - 2 * p + 1)
					* (num - 2 * p + 2)) / 2;
			return r + tri;
		}
	}
}
