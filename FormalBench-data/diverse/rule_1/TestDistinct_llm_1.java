
import java.io.*;
import java.lang.*;
import java.math.*;

class TestDistinct {

	public static Boolean testDistinct(int[] arr) {

		boolean[] result = new boolean[101];

		for (int index = 0; index < arr.length; index++) {
			if (arr[index] < 0
					|| arr[index] >= result.length) {
				throw new IllegalArgumentException("Number out of range: " + arr[index]);
			}
			if (result[arr[index]]) {
				return false;
			}
			result[arr[index]] = true;
		}
		return true;
	}
}
