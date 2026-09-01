
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenOdd {

	public static int sumEvenOdd(int[] arr) {
		int sum = -1;
		int last = -1;

		for (int i : arr) {
			if (sum == -1 && i % 2 == 0) {
				sum = i;
			}
			if (last == -1 && i % 2 != 0) {
				last = i;
			}
			if (sum != -1 && last != -1) {
				break;
			}
		}
		return sum + last;
	}
}
