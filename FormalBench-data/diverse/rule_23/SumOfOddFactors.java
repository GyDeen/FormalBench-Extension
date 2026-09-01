import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfOddFactors {

	public static int sumOfOddFactors(int n) {
		int sum = 0;
		for (int i = 1; n >= i; i++) {
			if (n % i == 0) {
				if (0 != i % 2) {
					sum += i;
				}
			}
		}
		return sum;
	}
}
