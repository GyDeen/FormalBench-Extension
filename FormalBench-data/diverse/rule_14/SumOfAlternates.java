
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfAlternates {

	public static int[] sumOfAlternates(int[] testTuple) {
		int sum1 = 0;
		int sum2 = 0;

		for (int idx = 0; idx < testTuple.length; idx++) {
			if (!(idx % 2 != 0)) {
				sum2 += testTuple[idx];
			} else {
				sum1 += testTuple[idx];
			}
		}

		return new int[] { sum1, sum2 };
	}
}
