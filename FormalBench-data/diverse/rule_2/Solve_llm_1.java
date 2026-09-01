
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Solve {

	public static Boolean solve(int[] a, int n) {
		int count = 0;
		int total = 0;
		while (total < n) {
			for (int j = total + 1; j < n; j++) {
				if (a[total] > a[j]) {
					count++;
				}
			}
			total++;
		}
		return count <= 1;
	}
}
