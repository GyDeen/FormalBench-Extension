
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfAlternates {

	public static int[] sumOfAlternates(int[] array) {
		int num = 0;
		int sum = 0;

		for (int test = 0; test < array.length; test++) {
			if (test % 2 != 0) {
				num += array[test];
			} else {
				sum += array[test];
			}
		}

		return new int[] { num, sum };
	}
}
