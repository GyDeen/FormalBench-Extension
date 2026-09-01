import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfOddFactors {

	public static int sumOfOddFactors(int n) {
		int sum = 0;
		int count = 1;
		while (count <= n) {
			if (n % count == 0) {
				if (count % 2 != 0) {
					sum += count;
				}
			}
			count++;
		}
		return sum;
	}
}
