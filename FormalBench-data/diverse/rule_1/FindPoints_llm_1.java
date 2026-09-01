
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindPoints {

	public static int[] findPoints(int Array, int l, int n, int r) {
		int[] Find = new int[2];
		if (Array < n && l < r) {
			Find[0] = Math.min(Array, l);
			Find[1] = Math.max(r, n);
		} else if (Array > n && l > r) {
			Find[0] = Math.min(n, r);
			Find[1] = Math.max(Array, l);
		} else {
			Find[0] = Array;
			Find[1] = l;
		}
		return Find;
	}
}
