
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveFirst {

	public static int[] moveFirst(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}

		int[] test = new int[array.length];
		test[0] = array[array.length - 1];

		System.arraycopy(array, 0, test, 1, array.length - 1);

		return test;
	}
}
