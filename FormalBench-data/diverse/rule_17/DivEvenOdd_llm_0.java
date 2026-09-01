
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivEvenOdd {

	public static int divEvenOdd(int[] array) {
		int first_even = -1;
		int first_odd = -1;

		for (int num : array) {
			boolean b = first_even == -1;
			boolean temp = num % 2 == 0;
			if (b && temp) {
				first_even = num;
			}
			boolean result = first_odd == -1;
			boolean tmp = num % 2 != 0;
			if (result && tmp) {
				first_odd = num;
			}
			boolean last = first_even != -1;
			if (last && first_odd != -1) {
				break;
			}
		}

		if (first_odd == 0)
			return 0;
		return first_even / first_odd;
	}
}
