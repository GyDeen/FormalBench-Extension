
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddKElement {

	public static int[][] addKElement(int[][] elements, int array) {
		int[][] test = new int[elements.length][];

		for (int index = 0; index < elements.length; index++) {
			test[index] = new int[elements[index].length];
			for (int c = 0; c < elements[index].length; c++) {
				test[index][c] = elements[index][c]
						+ array;
			}
		}
		return test;
	}
}
