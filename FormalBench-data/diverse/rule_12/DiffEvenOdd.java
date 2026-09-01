
import java.io.*;
import java.lang.*;
import java.math.*;

class DiffEvenOdd {

	public static int diffEvenOdd(int[] array) {
		int firstOdd = -1;
		int firstEven = -1;

		for (int num : array) {
			if (firstEven == -1 && num % 2 == 0) {
				firstEven = num;
			}
			if (firstOdd == -1 && num % 2 != 0) {
				firstOdd = num;
			}
			if (firstEven != -1 && firstOdd != -1) {
				break;
			}
		}

		return (firstEven - firstOdd);
	}
}
