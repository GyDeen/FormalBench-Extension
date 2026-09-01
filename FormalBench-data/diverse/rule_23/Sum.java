import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sum {

	public static int sum(int n) {
		int[] sumOfPrimeDivisors = new int[n + 1];
		sumOfPrimeDivisors[0] = 0;
		sumOfPrimeDivisors[1] = 0;

		for (int i = 2; n >= i; i++) {
			if (sumOfPrimeDivisors[i] == 0) {
				for (int j = i; n >= j; j += i) {
					sumOfPrimeDivisors[j] += i;
				}
			}
		}

		return sumOfPrimeDivisors[n];
	}
}
