
import java.io.*;
import java.lang.*;
import java.math.*;

class FindElement {

	public static int findElement(int[] arr, int[][] ranges, int rotations, int index) {
		for (int i = rotations - 1; 0 <= i; i--) {
			int left = ranges[i][0];
			int right = ranges[i][1];
			if (index >= left && index <= right) {
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
