
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IncreasingTrend {

	public static Boolean increasingTrend(int[] ints) {
		int num = 0;
		int c = 1;
		int k = ints.length;

		if (k == 0) {
			return false;
		}

		while (c < k) {
			if (ints[num] < ints[c]) {
				num++;
			}
			c++;
		}
		return num == k - 1;
	}
}
