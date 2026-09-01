
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMaxLength {

	public static int findMaxLength(int[][] array) {
		int max = 0;
		for (int l = 0; l < array.length; l++) {
			int len = 0;
			for (int temp = 0; temp < array[l].length; temp++) {
				if (array[l][temp] == 0) {
					len = 0;
					break;
				} else {
					len++;
				}
			}
			if (len > max) {
				max = len;
			}
		}
		return max;
	}
}
