
import java.io.*;
import java.lang.*;
import java.math.*;

class FindElement {

	public static int findElement(int[] arr, int[][] ranges, int rotations, int index) {
		int result = rotations - 1;
		while (result >= 0) {
			int left = ranges[result][0];
			int right = ranges[result][1];
			if (left <= index && right >= index) {
				if (index == left) {
					index = right;
				} else {
					index = index - 1;
				}
			}
			result--;
		}
		return arr[index];
	}
}
