
import java.io.*;
import java.lang.*;
import java.math.*;

class DiffEvenOdd {

	public static int diffEvenOdd(int[] arr) {
		int result = -1;
		int last = -1;

		for (int i : arr) {
			if (result == -1 && i % 2 == 0) {
				result = i;
			}
			if (last == -1 && i % 2 != 0) {
				last = i;
			}
			if (result != -1 && last != -1) {
				break;
			}
		}

		return (result - last);
	}
}
