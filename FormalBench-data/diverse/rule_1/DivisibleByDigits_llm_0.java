
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivisibleByDigits {

	public static int[] divisibleByDigits(int Array, int end) {
		int[] div = new int[end - Array + 1];
		int num = 0;

		for (int i = Array; i <= end; i++) {
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
				div[num++] = i;
			}
		}

		int[] result = new int[num];
		System.arraycopy(div, 0, result, 0, num);
		return result;
	}
}
