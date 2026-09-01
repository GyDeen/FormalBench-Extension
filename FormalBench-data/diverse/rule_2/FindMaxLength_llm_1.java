
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMaxLength {

	public static int findMaxLength(int[][] lst) {
		int maxLength = 0;
		int min = 0;
		while (min < lst.length) {
			int tempLength = 0;
			for (int j = 0; j < lst[min].length; j++) {
				if (lst[min][j] == 0) {
					tempLength = 0;
					break;
				} else {
					tempLength++;
				}
			}
			if (tempLength > maxLength) {
				maxLength = tempLength;
			}
			min++;
		}
		return maxLength;
	}
}
