
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddKElement {

	public static int[][] addKElement(int[][] list, int array) {
		int[][] test = new int[list.length][];

		for (int index = 0; index < list.length; index++) {
			test[index] = new int[list[index].length];
			for (int c = 0; c < list[index].length; c++) {
				test[index][c] = list[index][c]
						+ array;
			}
		}
		return test;
	}
}
