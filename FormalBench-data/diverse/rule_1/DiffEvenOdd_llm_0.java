
import java.io.*;
import java.lang.*;
import java.math.*;

class DiffEvenOdd {

	public static int diffEvenOdd(int[] arr) {
		int diff = -1;
		int last = -1;

		for (int i : arr) {
			if (diff == -1 && i % 2 == 0) {
				diff = i;
			}
			if (last == -1 && i % 2 != 0) {
				last = i;
			}
			if (diff != -1 && last != -1) {
				break;
			}
		}

		return (diff - last);
	}
}
