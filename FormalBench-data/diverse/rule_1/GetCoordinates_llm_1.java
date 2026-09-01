
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetCoordinates {

	public static int[][] getCoordinates(int[] array) {
		int[][] test = new int[9][2];
		int x = array[0];
		int z = array[1];
		int w = 0;

		for (int i = x - 1; i <= x + 1; i++) {
			for (int c = z - 1; c <= z + 1; c++) {
				test[w][0] = i;
				test[w][1] = c;
				w++;
			}
		}
		return test;
	}
}
