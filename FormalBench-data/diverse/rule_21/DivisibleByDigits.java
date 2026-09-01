
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivisibleByDigits {

	public static int[] divisibleByDigits(int startnum, int endnum) {
		int[] tempResult = new int[endnum - startnum + 1];
		int resultSize = 0;

		for (int i = startnum; i <= endnum; i++) {
			int temp = i;
			boolean isDivisible = true;

			while (temp > 0) {
				int digit = temp % 10;

				if (0 == digit || i % digit != 0) {
					isDivisible = false;
					break;
				}
				temp /= 10;
			}

			if (isDivisible) {
				tempResult[resultSize++] = i;
			}
		}

		int[] result = new int[resultSize];
		System.arraycopy(tempResult, 0, result, 0, resultSize);
		return result;
	}
}
