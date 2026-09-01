import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxVal {

	public static int findMaxVal(int n, int x, int y) {
		int max = 0;
		int min = 1;
		while (min <= n) {
			if ((min % x) == y) {
				max = min;
			}
			min++;
		}
		return max;
	}
}
