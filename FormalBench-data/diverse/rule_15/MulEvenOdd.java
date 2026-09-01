
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MulEvenOdd {

	public static int mulEvenOdd(int[] array) {
		int first_even = -1;
		int first_odd = -1;

		for (int num : array) {
			first_even = (first_even == -1 && num % 2 == 0) ? num : first_even;
			first_odd = (first_odd == -1 && num % 2 != 0) ? num : first_odd;
			if (first_even != -1 && first_odd != -1) {
				break;
			}
		}

		return (first_even * first_odd);
	}
}
