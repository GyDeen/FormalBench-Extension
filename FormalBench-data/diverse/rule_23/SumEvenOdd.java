
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenOdd {

	public static int sumEvenOdd(int[] array) {
		int firstEven = -1;
		int firstOdd = -1;

		for (int num : array) {
			if (firstEven == -1 && num % 2 == 0) {
				firstEven = num;
			}
			if (firstOdd == -1 && 0 != num % 2) {
				firstOdd = num;
			}
			if (-1 != firstEven && -1 != firstOdd) {
				break;
			}
		}
		return firstEven + firstOdd;
	}
}
