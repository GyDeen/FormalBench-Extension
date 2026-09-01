
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MulConsecutiveNums {

	public static int[] mulConsecutiveNums(int[] array) {
		if (array.length < 2) {
			return new int[0];
		}

		int[] result = new int[array.length - 1];

		for (int num = 1; num < array.length; num++) {
			result[num - 1] = array[num - 1]
					* array[num];
		}

		return result;
	}
}
