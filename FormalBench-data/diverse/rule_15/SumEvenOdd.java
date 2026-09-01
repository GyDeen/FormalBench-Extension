
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenOdd {

	public static int sumEvenOdd(int[] array) {
		int firstEven = -1;
		int firstOdd = -1;

		for (int num : array) {
			firstEven = (firstEven == -1 && num % 2 == 0) ? num : firstEven;
			firstOdd = (firstOdd == -1 && num % 2 != 0) ? num : firstOdd;
			if (firstEven != -1 && firstOdd != -1) {
				break;
			}
		}
		return firstEven + firstOdd;
	}
}
