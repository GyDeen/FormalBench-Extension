
import java.io.*;
import java.lang.*;
import java.math.*;

class FindElement {

	public static int findElement(int[] arr, int[][] ranges, int rotations, int index) {
		int i = rotations - 1;
		while (i >= 0) {
			int left = ranges[i][0];
			int right = ranges[i][1];
			if (left <= index && right >= index) {
				if (index == left) {
					index = right;
				} else {
					index = index - 1;
				}
			}
			i--;
		}
		return arr[index];
	}
}
