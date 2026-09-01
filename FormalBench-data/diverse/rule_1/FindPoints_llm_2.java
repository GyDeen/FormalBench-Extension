
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindPoints {

	public static int[] findPoints(int Array, int L, int n, int r) {
		int[] find = new int[2];
		if (Array < n && L < r) {
			find[0] = Math.min(Array, L);
			find[1] = Math.max(r, n);
		} else if (Array > n && L > r) {
			find[0] = Math.min(n, r);
			find[1] = Math.max(Array, L);
		} else {
			find[0] = Array;
			find[1] = L;
		}
		return find;
	}
}
