
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckSmaller {

	public static Boolean checkSmaller(int[] arr, int[] test) {
		if (arr.length != test.length) {
			throw new IllegalArgumentException("Arrays must have the same length.");
		}

		for (int index = 0; index < test.length; index++) {
			if (arr[index] > test[index]) {
				continue;
			} else if (arr[index] <= test[index]) {
				return false;
			}
		}
		return true;
	}
}
