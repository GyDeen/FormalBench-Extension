
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReArrangeTuples {

	public static int[][] reArrangeTuples(int[][] array, int[] ordArray) {
		int num = ordArray.length;
		int[][] result = new int[num][2];

		int index = 0;

		for (int count = 0; count < num; count++) {
			for (int test = 0; test < array.length; test++) {
				if (array[test][0] == ordArray[count]) {
					result[index++] = array[test];
					break;
				}
			}
		}

		return result;
	}
}
