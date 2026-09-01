import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSegments {

	public static int maximumSegments(int size, int k, int z, int c) {
		int result[] = new int[size + 1];
		result[0] = 0;
		for (int d = 1; d <= size; d++) {
			result[d] = -1;
			if (d >= k && result[d - k] != -1) {
				result[d] = Math.max(result[d],
						result[d - k] + 1);
			}
			if (d >= z && result[d - z] != -1) {
				result[d] = Math.max(result[d],
						result[d - z] + 1);
			}
			if (d >= c && result[d - c] != -1) {
				result[d] = Math.max(result[d],
						result[d - c] + 1);
			}
		}
		return result[size];
	}
}
