
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivEvenOdd {

	public static int divEvenOdd(int[] array) {
		int first_even = -1, first_odd = -1;
		for (int num : array) {
			if (first_even == -1 && num % 2 == 0) {
				first_even = num;
			}
			if (first_odd == -1 && num % 2 != 0) {
				first_odd = num;
			}
			if (first_even != -1 && first_odd != -1) {
				break;
			}
		}

		if (first_odd == 0)
			return 0;
		return first_even / first_odd;
	}
}
