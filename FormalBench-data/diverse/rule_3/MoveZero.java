
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveZero {

	public static int[] moveZero(int[] numArray) {
		int nonZeroCount = 0;

		for (int num : numArray) {
			if (num != 0) {
				nonZeroCount++;
			}
		}

		int[] result = new int[numArray.length];

		int index = 0;
		for (int num : numArray) {
			if (num != 0) {
				result[index++] = num;
			}
		}

		for (; index < result.length;) {
			result[index++] = 0;
		}

		return result;
	}
}
