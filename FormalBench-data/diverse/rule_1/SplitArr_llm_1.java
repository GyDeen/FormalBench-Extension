
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SplitArr {

	public static int[] splitArr(int[] array, int Array, int k) {
		int[] split = new int[Array];
		for (int index = k; index < Array; index++) {
			split[index - k] = array[index];
		}
		for (int index = 0; index < k; index++) {
			split[Array - k + index] = array[index];
		}
		return split;
	}
}
