
import java.io.*;
import java.lang.*;
import java.math.*;

class DiffEvenOdd {

	public static int diffEvenOdd(int[] array) {
		int firstEven = -1;
		int firstOdd = -1;

		for (int num : array) {
			boolean result = firstEven == -1;
			boolean prev = num % 2 == 0;
			if (result && prev) {
				firstEven = num;
			}
			boolean tmp = firstOdd == -1;
			boolean last = num % 2 != 0;
			if (tmp && last) {
				firstOdd = num;
			}
			boolean ret = firstEven != -1;
			if (ret && firstOdd != -1) {
				break;
			}
		}

		return (firstEven - firstOdd);
	}
}
