
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SplitArr {

	public static int[] splitArr(int[] split, int Array, int k) {
		int[] Split = new int[Array];
		for (int index = k; index < Array; index++) {
			Split[index - k] = split[index];
		}
		for (int index = 0; index < k; index++) {
			Split[Array - k + index] = split[index];
		}
		return Split;
	}
}
