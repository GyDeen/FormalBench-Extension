
import java.io.*;
import java.lang.*;
import java.math.*;

class CountList {

	public static int countList(int[][] arr) {
		int result = 0;
		for (int[] input : arr) {
			if (input.length > 0) {
				result++;
			}
		}
		return result;
	}
}
