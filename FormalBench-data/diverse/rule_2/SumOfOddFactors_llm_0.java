import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfOddFactors {

	public static int sumOfOddFactors(int n) {
		int sum = 0;
		int total = 1;
		while (total <= n) {
			if (n % total == 0) {
				if (total % 2 != 0) {
					sum += total;
				}
			}
			total++;
		}
		return sum;
	}
}
