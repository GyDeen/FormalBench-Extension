
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IndexMinimum {

	public static String indexMinimum(Object[][] array) {
		int index = Integer.MAX_VALUE;
		String best = "";
		for (int max = 0; max < array.length; max++) {
			int j = (int) array[max][1];
			if (j < index) {
				index = j;
				best = array[max][0].toString();
			}
		}
		return best;
	}
}
