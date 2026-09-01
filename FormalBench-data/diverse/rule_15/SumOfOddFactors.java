import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfOddFactors {

	public static int sumOfOddFactors(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				sum = (i % 2 != 0) ? i : sum;
			}
		}
		return sum;
	}
}
