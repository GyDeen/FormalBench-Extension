
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IncreasingTrend {

	public static Boolean increasingTrend(int[] ints) {
		int index = 0;
		int n = 1;
		int k = ints.length;

		if (k == 0) {
			return false;
		}

		while (n < k) {
			if (ints[index] < ints[n]) {
				index++;
			}
			n++;
		}
		return index == k - 1;
	}
}
