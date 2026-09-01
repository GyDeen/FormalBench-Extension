
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfAlternates {

	public static int[] sumOfAlternates(int[] array) {
		int test = 0;
		int sum = 0;

		for (int i = 0; i < array.length; i++) {
			if (i % 2 != 0) {
				test += array[i];
			} else {
				sum += array[i];
			}
		}

		return new int[] { test, sum };
	}
}
