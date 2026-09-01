
import java.io.*;
import java.lang.*;
import java.math.*;

class FindElement {

	public static int findElement(int[] arr, int[][] ranges, int array, int width) {
		for (int r = array - 1; r >= 0; r--) {
			int j = ranges[r][0];
			int top = ranges[r][1];
			if (j <= width && top >= width) {
				if (width == j) {
					width = top;
				} else {
					width = width - 1;
				}
			}
		}
		return arr[width];
	}
}
