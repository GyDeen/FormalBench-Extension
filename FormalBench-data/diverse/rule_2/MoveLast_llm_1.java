
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

		int num = 1;
		while (num < size) {
			result[num - 1] = numArray[num];
			num++;
		}
		result[size - 1] = numArray[0];

		return result;
	}
}
