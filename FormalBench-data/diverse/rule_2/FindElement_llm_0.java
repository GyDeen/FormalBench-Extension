
import java.io.*;
import java.lang.*;
import java.math.*;

class FindElement {

	public static int findElement(int[] arr, int[][] ranges, int rotations, int index) {
		int found = rotations - 1;
		while (found >= 0) {
			int left = ranges[found][0];
			int right = ranges[found][1];
			if (left <= index && right >= index) {
				if (index == left) {
					index = right;
				} else {
					index = index - 1;
				}
			}
			found--;
		}
		return arr[index];
	}
}
