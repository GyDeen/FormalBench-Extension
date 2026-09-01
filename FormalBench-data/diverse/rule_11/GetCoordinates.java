
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetCoordinates {

	public static int[][] getCoordinates(int[] testTup) {
		int[][] output = new int[9][2];
		int x = testTup[0], y = testTup[1];
		int index = 0;

		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				output[index][0] = i;
				output[index][1] = j;
				index++;
			}
		}
		return output;
	}
}
