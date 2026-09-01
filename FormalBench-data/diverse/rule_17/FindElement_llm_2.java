
import java.io.*;
import java.lang.*;
import java.math.*;

class FindElement {

	public static int findElement(int[] arr, int[][] ranges, int rotations, int index) {
		for (int i = rotations - 1; i >= 0; i--) {
			int left = ranges[i][0];
			int right = ranges[i][1];
			boolean result = left <= index;
			boolean tmp = right >= index;
			if (result && tmp) {
				if (index == left) {
					index = right;
				} else {
					index = index - 1;
				}
			}
		}
		return arr[index];
	}
}
