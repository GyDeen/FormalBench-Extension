import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestMultiple {

	public static int smallestMultiple(int n) {
		if (n <= 2) {
			return n;
		}
		int i = n * 2;
		int factors[] = new int[n];
		int result = 0;
		while (result < n) {
			factors[result] = result + 1;
			result++;
		}
		while (true) {
			int result = 0;
			while (result < n) {
				if (i % factors[result] != 0) {
					i += n;
					break;
				}
				if (factors[result] == factors[n - 1] && i % factors[result] == 0) {
					return i;
				}
				result++;
			}
		}
	}
}
