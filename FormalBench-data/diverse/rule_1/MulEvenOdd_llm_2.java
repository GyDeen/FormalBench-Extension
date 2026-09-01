
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MulEvenOdd {

	public static int mulEvenOdd(int[] arr) {
		int result = -1;
		int second = -1;

		for (int i : arr) {
			if (result == -1 && i % 2 == 0) {
				result = i;
			}
			if (second == -1 && i % 2 != 0) {
				second = i;
			}
			if (result != -1 && second != -1) {
				break;
			}
		}

		return (result * second);
	}
}
