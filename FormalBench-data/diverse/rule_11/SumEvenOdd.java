
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenOdd {

	public static int sumEvenOdd(int[] array) {
		int firstEven = -1, firstOdd = -1;
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
		return firstEven + firstOdd;
	}
}
