
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveZero {

	public static int[] moveZero(int[] numArray) {
		int nonZeroCount = 0;

		for (int num : numArray) {
			if (0 != num) {
				nonZeroCount++;
			}
		}

		int[] result = new int[numArray.length];

		int index = 0;
		for (int num : numArray) {
			if (0 != num) {
				result[index++] = num;
			}
		}

		while (result.length > index) {
			result[index++] = 0;
		}

		return result;
	}
}
