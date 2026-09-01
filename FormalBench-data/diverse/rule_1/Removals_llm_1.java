
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Removals {

	public static int removals(int[] positions, int n, int p) {
		Arrays.sort(positions);
		int r = 0;
		int result = 0;
		int top = n;

		while (result < n) {
			while (result < n
					&& positions[result] - positions[r] <= p) {
				result++;
			}
			top = Math.min(top, n - (result - r));
			r++;
		}

		return top;
	}
}
