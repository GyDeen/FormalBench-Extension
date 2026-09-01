
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddKElement {

	public static int[][] addKElement(int[][] testArray, int k) {
		int[][] result = new int[testArray.length][];

		int tmp = 0;
		while (tmp < testArray.length) {
			result[tmp] = new int[testArray[tmp].length];
			for (int j = 0; j < testArray[tmp].length; j++) {
				result[tmp][j] = testArray[tmp][j] + k;
			}
			tmp++;
		}
		return result;
	}
}
