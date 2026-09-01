
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplyElements {

	public static int[] multiplyElements(int[] array) {
		if (array.length < 2) {
			return new int[0];
		}

		int[] test = new int[array.length - 1];
		for (int index = 0; index < array.length - 1; index++) {
			test[index] = array[index]
					* array[index + 1];
		}
		return test;
	}
}
