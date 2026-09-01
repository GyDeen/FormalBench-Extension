
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveLast {

	public static int[] moveLast(int[] numArray) {
		int size = numArray.length;
		if (0 == size) {
			return numArray;
		}

		int[] result = new int[size];

		for (int i = 1; i < size; i++) {
			result[i - 1] = numArray[i];
		}
		result[size - 1] = numArray[0];

		return result;
	}
}
