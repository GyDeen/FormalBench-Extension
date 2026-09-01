
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfAlternates {

	public static int[] sumOfAlternates(int[] testTuple) {
		int sum1 = 0;
		int sum2 = 0;

		for (int idx = 0; idx < testTuple.length; idx++) {
			int id = idx % 2;
			if (id != 0) {
				sum1 += testTuple[idx];
			} else {
				sum2 += testTuple[idx];
			}
		}

		return new int[] { sum1, sum2 };
	}
}
