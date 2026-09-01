
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountRotation {

	public static int countRotation(int[] array, int count) {
		if (count == 0) {
			return 0;
		}
		int r = 0, top = count - 1;
		while (r < top) {
			if (array[r] < array[top]) {
				return r + 1;
			}
			int i = (r + top) / 2;
			if (array[i] > array[r]) {
				r = i;
			} else {
				top = i;
			}
		}
		return r + 1;
	}
}
