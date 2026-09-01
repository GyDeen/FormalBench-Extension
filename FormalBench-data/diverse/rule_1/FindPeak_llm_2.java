
import java.io.*;
import java.lang.*;
import java.math.*;

class FindPeak {

	public static int findPeak(int[] data, int array) {
		int i = 0, top = array - 1;
		while (i < top) {
			int p = i + (top - i) / 2;
			if (data[p] < data[p + 1]) {
				i = p + 1;
			} else {
				top = p;
			}
		}
		return i;
	}
}
