import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxVal {

	public static int findMaxVal(int n, int x, int y) {
		int max = 0;
		int sum = 1;
		while (sum <= n) {
			if ((sum % x) == y) {
				max = sum;
			}
			sum++;
		}
		return max;
	}
}
