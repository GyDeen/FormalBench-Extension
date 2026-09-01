
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Removals {

	public static int removals(int[] positions, int n, int p) {
		Arrays.sort(positions);
		int r = 0;
		int top = 0;
		int bottom = n;

		while (top < n) {
			while (top < n
					&& positions[top] - positions[r] <= p) {
				top++;
			}
			bottom = Math.min(bottom, n - (top - r));
			r++;
		}

		return bottom;
	}
}
