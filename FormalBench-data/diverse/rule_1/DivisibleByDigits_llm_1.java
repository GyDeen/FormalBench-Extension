
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivisibleByDigits {

	public static int[] divisibleByDigits(int num, int end) {
		int[] div = new int[end - num + 1];
		int index = 0;

		for (int i = num; i <= end; i++) {
			int temp = i;
			boolean isDivisible = true;

			while (temp > 0) {
				int tmp = temp % 10;

				if (tmp == 0 || i % tmp != 0) {
					isDivisible = false;
					break;
				}
				temp /= 10;
			}

			if (isDivisible) {
				div[index++] = i;
			}
		}

		int[] result = new int[index];
		System.arraycopy(div, 0, result, 0, index);
		return result;
	}
}
