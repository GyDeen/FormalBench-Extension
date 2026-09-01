
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MulEvenOdd {

	public static int mulEvenOdd(int[] arr) {
		int result = -1;
		int last = -1;

		for (int index : arr) {
			if (result == -1 && index % 2 == 0) {
				result = index;
			}
			if (last == -1 && index % 2 != 0) {
				last = index;
			}
			if (result != -1 && last != -1) {
				break;
			}
		}

		return (result * last);
	}
}
