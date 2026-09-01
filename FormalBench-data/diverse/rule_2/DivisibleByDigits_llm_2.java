
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivisibleByDigits {

	public static int[] divisibleByDigits(int startnum, int endnum) {
		int[] tempResult = new int[endnum - startnum + 1];
		int resultSize = 0;

		int count = startnum;
		while (count <= endnum) {
			int temp = count;
			boolean isDivisible = true;
			while (temp > 0) {
				int digit = temp % 10;
				if (digit == 0 || count % digit != 0) {
					isDivisible = false;
					break;
				}
				temp /= 10;
			}
			if (isDivisible) {
				tempResult[resultSize++] = count;
			}
			count++;
		}

		int[] result = new int[resultSize];
		System.arraycopy(tempResult, 0, result, 0, resultSize);
		return result;
	}
}
