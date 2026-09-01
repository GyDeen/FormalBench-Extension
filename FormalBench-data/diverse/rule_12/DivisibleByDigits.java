
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivisibleByDigits {

	public static int[] divisibleByDigits(int startnum, int endnum) {
		int resultSize = 0;
		int[] tempResult = new int[endnum - startnum + 1];

		for (int i = startnum; i <= endnum; i++) {
			boolean isDivisible = true;
			int temp = i;

			while (temp > 0) {
				int digit = temp % 10;

				temp /= 10;
				if (digit == 0 || i % digit != 0) {
					isDivisible = false;
					break;
				}
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
