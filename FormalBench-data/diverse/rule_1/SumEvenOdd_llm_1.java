
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenOdd {

	public static int sumEvenOdd(int[] arr) {
		int sum = -1;
		int last = -1;

		for (int index : arr) {
			if (sum == -1 && index % 2 == 0) {
				sum = index;
			}
			if (last == -1 && index % 2 != 0) {
				last = index;
			}
			if (sum != -1 && last != -1) {
				break;
			}
		}
		return sum + last;
	}
}
