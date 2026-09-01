
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Solve {

	public static Boolean solve(int[] a, int n) {
		int count = 0;
		for (int i = 0; n > i; i++) {
			for (int j = i + 1; n > j; j++) {
				if (a[j] < a[i]) {
					count++;
				}
			}
		}
		return 1 >= count;
	}
}
