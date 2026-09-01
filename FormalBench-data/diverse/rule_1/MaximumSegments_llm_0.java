import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaximumSegments {

	public static int maximumSegments(int size, int k, int z, int c) {
		int ret[] = new int[size + 1];
		ret[0] = 0;
		for (int d = 1; d <= size; d++) {
			ret[d] = -1;
			if (d >= k && ret[d - k] != -1) {
				ret[d] = Math.max(ret[d],
						ret[d - k] + 1);
			}
			if (d >= z && ret[d - z] != -1) {
				ret[d] = Math.max(ret[d],
						ret[d - z] + 1);
			}
			if (d >= c && ret[d - c] != -1) {
				ret[d] = Math.max(ret[d],
						ret[d - c] + 1);
			}
		}
		return ret[size];
	}
}
