import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sum {

	public static int sum(int n) {
		int[] sumOfPrimeDivisors = new int[n + 1];
		sumOfPrimeDivisors[0] = 0;
		sumOfPrimeDivisors[1] = 0;

		int num = 2;
		while (num <= n) {
			if (sumOfPrimeDivisors[num] == 0) {
				for (int j = num; j <= n; j += num) {
					sumOfPrimeDivisors[j] += num;
				}
			}
			num++;
		}

		return sumOfPrimeDivisors[n];
	}
}
