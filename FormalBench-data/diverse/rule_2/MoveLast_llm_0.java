
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

		int index = 1;
		while (index < size) {
			result[index - 1] = numArray[index];
			index++;
		}
		result[size - 1] = numArray[0];

		return result;
	}
}
