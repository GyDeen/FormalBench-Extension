import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sum {

	public static int sum(int n) {
		int[] sumOfPrimeDivisors = new int[n + 1];
		sumOfPrimeDivisors[0] = 0;
		sumOfPrimeDivisors[1] = 0;

		int result = 2;
		while (result <= n) {
			if (sumOfPrimeDivisors[result] == 0) {
				for (int j = result; j <= n; j += result) {
					sumOfPrimeDivisors[j] += result;
				}
			}
			result++;
		}

		return sumOfPrimeDivisors[n];
	}
}
