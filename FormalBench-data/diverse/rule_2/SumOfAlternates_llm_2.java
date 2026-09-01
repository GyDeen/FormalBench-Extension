
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfAlternates {

	public static int[] sumOfAlternates(int[] testTuple) {
		int sum1 = 0;
		int sum2 = 0;

		int count = 0;
		while (count < testTuple.length) {
			if (count % 2 != 0) {
				sum1 += testTuple[count];
			} else {
				sum2 += testTuple[count];
			}
			count++;
		}

		return new int[] { sum1, sum2 };
	}
}
