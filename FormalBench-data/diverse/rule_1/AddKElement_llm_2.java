
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddKElement {

	public static int[][] addKElement(int[][] elements, int array) {
		int[][] test = new int[elements.length][];

		for (int index = 0; index < elements.length; index++) {
			test[index] = new int[elements[index].length];
			for (int n = 0; n < elements[index].length; n++) {
				test[index][n] = elements[index][n]
						+ array;
			}
		}
		return test;
	}
}
