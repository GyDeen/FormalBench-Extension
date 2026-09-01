
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestPos {

	public static int largestPos(int[] array) {
		int max = 0;
		int min = 0;
		while (min < array.length) {
			if (array[min] > 0 && array[min] > max) {
				max = array[min];
			}
			min++;
		}
		return max;
	}
}
