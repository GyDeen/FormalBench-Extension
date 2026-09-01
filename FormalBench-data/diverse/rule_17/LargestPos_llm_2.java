
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestPos {

	public static int largestPos(int[] array) {
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			boolean result = array[i] > 0;
			boolean tmp = array[i] > max;
			if (result && tmp) {
				max = array[i];
			}
		}
		return max;
	}
}
