
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfAlternates {

	public static int[] sumOfAlternates(int[] array) {
		int num = 0;
		int count = 0;

		for (int test = 0; test < array.length; test++) {
			if (test % 2 != 0) {
				num += array[test];
			} else {
				count += array[test];
			}
		}

		return new int[] { num, count };
	}
}
