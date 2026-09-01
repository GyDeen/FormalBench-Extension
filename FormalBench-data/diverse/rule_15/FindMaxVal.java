import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxVal {

	public static int findMaxVal(int n, int x, int y) {
		int max = 0;
		for (int i = 1; i <= n; i++) {
			max = ((i % x) == y) ? i : max;
		}
		return max;
	}
}
