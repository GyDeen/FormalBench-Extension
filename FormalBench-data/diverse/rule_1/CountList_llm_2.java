
import java.io.*;
import java.lang.*;
import java.math.*;

class CountList {

	public static int countList(int[][] arr) {
		int result = 0;
		for (int[] i : arr) {
			if (i.length > 0) {
				result++;
			}
		}
		return result;
	}
}
