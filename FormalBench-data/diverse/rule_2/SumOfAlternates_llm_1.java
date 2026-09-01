
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfAlternates {

	public static int[] sumOfAlternates(int[] testTuple) {
		int sum1 = 0;
		int sum2 = 0;

		int num = 0;
		while (num < testTuple.length) {
			if (num % 2 != 0) {
				sum1 += testTuple[num];
			} else {
				sum2 += testTuple[num];
			}
			num++;
		}

		return new int[] { sum1, sum2 };
	}
}
