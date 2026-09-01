
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestPos {

	public static int largestPos(int[] array) {
		int max = 0;
		int offset = 0;
		while (offset < array.length) {
			if (array[offset] > 0 && array[offset] > max) {
				max = array[offset];
			}
			offset++;
		}
		return max;
	}
}
