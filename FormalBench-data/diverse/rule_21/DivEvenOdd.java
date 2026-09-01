
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivEvenOdd {

	public static int divEvenOdd(int[] array) {
		int first_even = -1;
		int first_odd = -1;

		for (int num : array) {
			if (-1 == first_even && 0 == num % 2) {
				first_even = num;
			}
			if (-1 == first_odd && num % 2 != 0) {
				first_odd = num;
			}
			if (first_even != -1 && first_odd != -1) {
				break;
			}
		}

		if (0 == first_odd)
			return 0;
		return first_even / first_odd;
	}
}
