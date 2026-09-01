
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestPos {

	public static int largestPos(int[] array) {
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			boolean b = array[i] > 0;
			boolean temp = array[i] > max;
			if (b && temp) {
				max = array[i];
			}
		}
		return max;
	}
}
