
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckGreater {

	public static Boolean checkGreater(int[] arr, int[] test) {
		if (arr.length != test.length) {
			throw new IllegalArgumentException("Arrays must be of the same length");
		}

		for (int index = 0; index < arr.length; index++) {
			if (test[index] <= arr[index]) {
				return false;
			}
		}
		return true;
	}
}
