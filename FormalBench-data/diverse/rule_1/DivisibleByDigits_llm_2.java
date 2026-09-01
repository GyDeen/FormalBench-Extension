
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivisibleByDigits {

	public static int[] divisibleByDigits(int num, int end) {
		int[] div = new int[end - num + 1];
		int tmp = 0;

		for (int i = num; i <= end; i++) {
			int temp = i;
			boolean isDivisible = true;

			while (temp > 0) {
				int index = temp % 10;

				if (index == 0 || i % index != 0) {
					isDivisible = false;
					break;
				}
				temp /= 10;
			}

			if (isDivisible) {
				div[tmp++] = i;
			}
		}

		int[] result = new int[tmp];
		System.arraycopy(div, 0, result, 0, tmp);
		return result;
	}
}
