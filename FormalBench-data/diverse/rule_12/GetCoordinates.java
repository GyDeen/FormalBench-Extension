
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetCoordinates {

	public static int[][] getCoordinates(int[] testTup) {
		int x = testTup[0];
		int[][] output = new int[9][2];
		int index = 0;
		int y = testTup[1];

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
