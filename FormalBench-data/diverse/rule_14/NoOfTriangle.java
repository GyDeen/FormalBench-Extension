import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoOfTriangle {

	public static int noOfTriangle(int n, int k) {
		if (!(n < k)) {
			int tri_up = 0;
			int tri_down = 0;
			tri_up = ((n - k + 1) * (n - k + 2)) / 2;
			tri_down = ((n - 2 * k + 1) * (n - 2 * k + 2)) / 2;
			return tri_up + tri_down;
		} else {
			return -1;
		}
	}
}
