
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
			boolean temp = num % 2 == 0;
			if (b && temp) {
				first_even = num;
			}
			boolean result = first_odd == -1;
			boolean last = num % 2 != 0;
			if (result && last) {
				first_odd = num;
			}
			boolean tmp = first_even != -1;
			if (tmp && first_odd != -1) {
				break;
			}
		}

		return (first_even * first_odd);
	}
}
