
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveLast {

	public static int[] moveLast(int[] numArray) {
		int size = numArray.length;
		if (size == 0) {
			return numArray;
		}

		int[] result = new int[size];

		int tmp = 1;
		while (tmp < size) {
			result[tmp - 1] = numArray[tmp];
			tmp++;
		}
		result[size - 1] = numArray[0];

		return result;
	}
}
