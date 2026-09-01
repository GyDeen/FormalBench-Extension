
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMaxLength {

	public static int findMaxLength(int[][] lst) {
		int maxLength = 0;
		int max = 0;
		while (max < lst.length) {
			int tempLength = 0;
			for (int j = 0; j < lst[max].length; j++) {
				if (lst[max][j] == 0) {
					tempLength = 0;
					break;
				} else {
					tempLength++;
				}
			}
			if (tempLength > maxLength) {
				maxLength = tempLength;
			}
			max++;
		}
		return maxLength;
	}
}
