
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
			boolean first = firstOdd == -1;
			boolean tmp = num % 2 != 0;
			if (first && tmp) {
				firstOdd = num;
			}
			boolean last = firstEven != -1;
			if (last && firstOdd != -1) {
				break;
			}
		}
		return firstEven + firstOdd;
	}
}
