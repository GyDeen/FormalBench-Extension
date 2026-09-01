import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxVal {

	public static int findMaxVal(int n, int x, int y) {
		int max = 0;
		for (int i = 1; i <= n; i++) {
			int val = i % x;
			if ((val) == y) {
				max = i;
			}
		}
		return max;
	}
}
