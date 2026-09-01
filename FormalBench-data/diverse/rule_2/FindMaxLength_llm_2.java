
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMaxLength {

	public static int findMaxLength(int[][] lst) {
		int maxLength = 0;
		int start = 0;
		while (start < lst.length) {
			int tempLength = 0;
			for (int j = 0; j < lst[start].length; j++) {
				if (lst[start][j] == 0) {
					tempLength = 0;
					break;
				} else {
					tempLength++;
				}
			}
			if (tempLength > maxLength) {
				maxLength = tempLength;
			}
			start++;
		}
		return maxLength;
	}
}
