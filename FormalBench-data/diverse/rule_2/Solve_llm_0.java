
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Solve {

	public static Boolean solve(int[] a, int n) {
		int count = 0;
		int result = 0;
		while (result < n) {
			for (int j = result + 1; j < n; j++) {
				if (a[result] > a[j]) {
					count++;
				}
			}
			result++;
		}
		return count <= 1;
	}
}
