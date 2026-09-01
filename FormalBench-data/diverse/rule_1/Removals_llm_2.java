
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Removals {

	public static int removals(int[] array, int n, int p) {
		Arrays.sort(array);
		int r = 0;
		int top = 0;
		int bottom = n;

		while (top < n) {
			while (top < n
					&& array[top] - array[r] <= p) {
				top++;
			}
			bottom = Math.min(bottom, n - (top - r));
			r++;
		}

		return bottom;
	}
}
