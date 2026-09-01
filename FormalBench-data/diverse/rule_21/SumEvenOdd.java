
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenOdd {

	public static int sumEvenOdd(int[] array) {
		int firstEven = -1;
		int firstOdd = -1;

		for (int num : array) {
			if (-1 == firstEven && 0 == num % 2) {
				firstEven = num;
			}
			if (-1 == firstOdd && num % 2 != 0) {
				firstOdd = num;
			}
			if (firstEven != -1 && firstOdd != -1) {
				break;
			}
		}
		return firstEven + firstOdd;
	}
}
