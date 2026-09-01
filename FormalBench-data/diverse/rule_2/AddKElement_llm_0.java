
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddKElement {

	public static int[][] addKElement(int[][] testArray, int k) {
		int[][] result = new int[testArray.length][];

		int index = 0;
		while (index < testArray.length) {
			result[index] = new int[testArray[index].length];
			for (int j = 0; j < testArray[index].length; j++) {
				result[index][j] = testArray[index][j] + k;
			}
			index++;
		}
		return result;
	}
}
