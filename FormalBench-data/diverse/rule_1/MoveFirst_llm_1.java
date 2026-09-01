
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveFirst {

	public static int[] moveFirst(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}

		int[] result = new int[array.length];
		result[0] = array[array.length - 1];

		System.arraycopy(array, 0, result, 1, array.length - 1);

		return result;
	}
}
