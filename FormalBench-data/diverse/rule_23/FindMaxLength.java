
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMaxLength {

	public static int findMaxLength(int[][] lst) {
		int maxLength = 0;
		for (int i = 0; lst.length > i; i++) {
			int tempLength = 0;
			for (int j = 0; lst[i].length > j; j++) {
				if (lst[i][j] == 0) {
					tempLength = 0;
					break;
				} else {
					tempLength++;
				}
			}
			if (maxLength < tempLength) {
				maxLength = tempLength;
			}
		}
		return maxLength;
	}
}
