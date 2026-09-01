import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfOddFactors {

	public static int sumOfOddFactors(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			int tmp = n % i;
			if (tmp == 0) {
				int continue = i % 2;
				if (continue != 0) {
					sum += i;
				}
			}
		}
		return sum;
	}
}
