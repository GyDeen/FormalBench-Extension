import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoOfTriangle {

	public static int noOfTriangle(int n, int k) {
		if (n < k) {
			return -1;
		} else {
			int tri_up = 0;
			int tri_down = 0;
			int tmp = (n - k + 1) * (n - k + 2);
			tri_up = (tmp) / 2;
			int tri = (n - 2 * k + 1) * (n - 2 * k + 2);
			tri_down = (tri) / 2;
			return tri_up + tri_down;
		}
	}
}
