import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfOddFactors {

	public static int sumOfOddFactors(int n) {
		int sum = 0;
		int result = 1;
		while (result <= n) {
			if (n % result == 0) {
				if (result % 2 != 0) {
					sum += result;
				}
			}
			result++;
		}
		return sum;
	}
}
