
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKProduct {

	public static int findKProduct(int[][] array, int index) {
		int result = 1;
		int prod = array.length;
		for (int j = 0; j < prod; j++) {
			result *= array[j][index];
		}
		return result;
	}
}
