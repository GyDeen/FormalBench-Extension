
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestPos {

	public static int largestPos(int[] array) {
		int max = 0;
		int current = 0;
		while (current < array.length) {
			if (array[current] > 0 && array[current] > max) {
				max = array[current];
			}
			current++;
		}
		return max;
	}
}
