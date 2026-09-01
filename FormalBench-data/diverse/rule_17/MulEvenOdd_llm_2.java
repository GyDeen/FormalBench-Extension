
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MulEvenOdd {

	public static int mulEvenOdd(int[] array) {
		int first_even = -1;
		int first_odd = -1;

		for (int num : array) {
			boolean b = first_even == -1;
			boolean prev = num % 2 == 0;
			if (b && prev) {
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

		return (first_even * first_odd);
	}
}
