
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddKElement {

	public static int[][] addKElement(int[][] testArray, int k) {
		int[][] result = new int[testArray.length][];

		for (int i = 0; testArray.length > i; i++) {
			result[i] = new int[testArray[i].length];
			for (int j = 0; testArray[i].length > j; j++) {
				result[i][j] = testArray[i][j] + k;
			}
		}
		return result;
	}
}
