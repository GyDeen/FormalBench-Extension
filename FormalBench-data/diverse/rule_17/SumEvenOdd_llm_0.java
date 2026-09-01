
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenOdd {

	public static int sumEvenOdd(int[] array) {
		int firstEven = -1;
		int firstOdd = -1;

		for (int num : array) {
			boolean b = firstEven == -1;
			boolean temp = num % 2 == 0;
			if (b && temp) {
				firstEven = num;
			}
			boolean last = firstOdd == -1;
			boolean tmp = num % 2 != 0;
			if (last && tmp) {
				firstOdd = num;
			}
			boolean sum = firstEven != -1;
			if (sum && firstOdd != -1) {
				break;
			}
		}
		return firstEven + firstOdd;
	}
}
