
import java.io.*;
import java.lang.*;
import java.util.Arrays;

class FloatSort {

	public static String[][] floatSort(String[][] string) {
		if (string.length == 0) {
			return string;
		}

		Arrays.sort(string, (Sort, c) -> Float
				.compare(Float.parseFloat(c[1]), Float.parseFloat(Sort[1])));

		return string;
	}
}
