import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BellNumber {

	public static int bellNumber(int number) {
		int[][] arr = new int[number + 1][number + 1];
		arr[0][0] = 1;
		for (int index = 1; index <= number; index++) {
			arr[index][0] = arr[index - 1][index - 1];
			for (int l = 1; l <= index; l++) {
				arr[index][l] = arr[index - 1][l
						- 1] + arr[index][l - 1];
			}
		}
		return arr[number][0];
	}
}
