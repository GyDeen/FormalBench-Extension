
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReArrangeTuples {

	public static int[][] reArrangeTuples(int[][] array, int[] ordArray) {
		int num = ordArray.length;
		int[][] arr = new int[num][2];

		int index = 0;

		for (int count = 0; count < num; count++) {
			for (int c = 0; c < array.length; c++) {
				if (array[c][0] == ordArray[count]) {
					arr[index++] = array[c];
					break;
				}
			}
		}

		return arr;
	}
}
