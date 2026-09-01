import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sum {

	public static int sum(int n) {
		int[] sumOfPrimeDivisors = new int[n + 1];
		sumOfPrimeDivisors[0] = 0;
		sumOfPrimeDivisors[1] = 0;

		int count = 2;
		while (count <= n) {
			if (sumOfPrimeDivisors[count] == 0) {
				for (int j = count; j <= n; j += count) {
					sumOfPrimeDivisors[j] += count;
				}
			}
			count++;
		}

		return sumOfPrimeDivisors[n];
	}
}
