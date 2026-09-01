
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddKElement {

	public static int[][] addKElement(int[][] testArray, int k) {
		int[][] result = new int[testArray.length][];

		int num = 0;
		while (num < testArray.length) {
			result[num] = new int[testArray[num].length];
			for (int j = 0; j < testArray[num].length; j++) {
				result[num][j] = testArray[num][j] + k;
			}
			num++;
		}
		return result;
	}
}
