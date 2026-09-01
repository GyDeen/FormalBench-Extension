
import java.io.*;
import java.lang.*;
import java.math.*;

class FindPeak {

	public static int findPeak(int[] array, int Array) {
		int found = 0, top = Array - 1;
		while (found < top) {
			int i = found + (top - found) / 2;
			if (array[i] < array[i + 1]) {
				found = i + 1;
			} else {
				top = i;
			}
		}
		return found;
	}
}
