
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetCoordinates {

	public static int[][] getCoordinates(int[] arr) {
		int[][] test = new int[9][2];
		int x = arr[0];
		int n = arr[1];
		int z = 0;

		for (int i = x - 1; i <= x + 1; i++) {
			for (int c = n - 1; c <= n + 1; c++) {
				test[z][0] = i;
				test[z][1] = c;
				z++;
			}
		}
		return test;
	}
}
