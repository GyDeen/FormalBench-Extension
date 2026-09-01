import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfOddFactors {

	public static int sumOfOddFactors(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			if (0 == n % i) {
				if (i % 2 != 0) {
					sum += i;
				}
			}
		}
		return sum;
	}
}
