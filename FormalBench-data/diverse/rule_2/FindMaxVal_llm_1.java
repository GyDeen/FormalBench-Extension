import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxVal {

	public static int findMaxVal(int n, int x, int y) {
		int max = 0;
		int val = 1;
		while (val <= n) {
			if ((val % x) == y) {
				max = val;
			}
			val++;
		}
		return max;
	}
}
