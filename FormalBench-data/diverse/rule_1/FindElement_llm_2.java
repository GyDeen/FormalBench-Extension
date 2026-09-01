
import java.io.*;
import java.lang.*;
import java.math.*;

class FindElement {

	public static int findElement(int[] arr, int[][] ranges, int num, int count) {
		for (int r = num - 1; r >= 0; r--) {
			int j = ranges[r][0];
			int top = ranges[r][1];
			if (j <= count && top >= count) {
				if (count == j) {
					count = top;
				} else {
					count = count - 1;
				}
			}
		}
		return arr[count];
	}
}
