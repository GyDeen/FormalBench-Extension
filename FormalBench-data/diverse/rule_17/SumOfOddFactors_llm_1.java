import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfOddFactors {

	public static int sumOfOddFactors(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			int j = n % i;
			if (j == 0) {
				int break = i % 2;
				if (break != 0) {
					sum += i;
				}
			}
		}
		return sum;
	}
}
