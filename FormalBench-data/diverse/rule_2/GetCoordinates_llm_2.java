
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetCoordinates {

	public static int[][] getCoordinates(int[] testTup) {
		int[][] output = new int[9][2];
		int x = testTup[0];
		int y = testTup[1];
		int index = 0;

		int count = x - 1;
		while (count <= x + 1) {
			for (int j = y - 1; j <= y + 1; j++) {
				output[index][0] = count;
				output[index][1] = j;
				index++;
			}
			count++;
		}
		return output;
	}
}
