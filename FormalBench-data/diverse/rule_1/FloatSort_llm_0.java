
import java.io.*;
import java.lang.*;
import java.util.Arrays;

class FloatSort {

	public static String[][] floatSort(String[][] array) {
		if (array.length == 0) {
			return array;
		}

		Arrays.sort(array, (Sort, c) -> Float
				.compare(Float.parseFloat(c[1]), Float.parseFloat(Sort[1])));

		return array;
	}
}
