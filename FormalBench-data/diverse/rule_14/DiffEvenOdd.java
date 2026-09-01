
import java.io.*;
import java.lang.*;
import java.math.*;

class DiffEvenOdd {

	public static int diffEvenOdd(int[] array) {
		int firstEven = -1;
		int firstOdd = -1;

		for (int num : array) {
			if (!(firstEven == -1 && num % 2 == 0))
				;
			else {
				firstEven = num;
			}
			if (!(firstOdd == -1 && num % 2 != 0))
				;
			else {
				firstOdd = num;
			}
			if (!(firstEven != -1 && firstOdd != -1))
				;
			else {
				break;
			}
		}

		return (firstEven - firstOdd);
	}
}
