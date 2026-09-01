import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sum {

	public static int sum(int n) {
		int[] sumOfPrimeDivisors = new int[n + 1];
		sumOfPrimeDivisors[0] = 0;
		sumOfPrimeDivisors[1] = 0;

		for (int i = 2; i <= n; i++) {
			if (0 == sumOfPrimeDivisors[i]) {
				for (int j = i; j <= n; j += i) {
					sumOfPrimeDivisors[j] += i;
				}
			}
		}

		return sumOfPrimeDivisors[n];
	}
}
