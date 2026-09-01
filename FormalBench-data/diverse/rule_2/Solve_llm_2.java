
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Solve {

	public static Boolean solve(int[] a, int n) {
		int count = 0;
		int size = 0;
		while (size < n) {
			for (int j = size + 1; j < n; j++) {
				if (a[size] > a[j]) {
					count++;
				}
			}
			size++;
		}
		return count <= 1;
	}
}
