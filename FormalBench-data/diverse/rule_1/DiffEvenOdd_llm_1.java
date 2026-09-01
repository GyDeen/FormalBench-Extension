
import java.io.*;
import java.lang.*;
import java.math.*;

class DiffEvenOdd {

	public static int diffEvenOdd(int[] arr) {
		int diff = -1;
		int last = -1;

		for (int index : arr) {
			if (diff == -1 && index % 2 == 0) {
				diff = index;
			}
			if (last == -1 && index % 2 != 0) {
				last = index;
			}
			if (diff != -1 && last != -1) {
				break;
			}
		}

		return (diff - last);
	}
}
