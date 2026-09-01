
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IndexMinimum {

	public static String indexMinimum(Object[][] testArray) {
		String minIndex = "";
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < testArray.length; i++) {
			int temp = (int) testArray[i][1];
			if (temp < min) {
				minIndex = testArray[i][0].toString();
				min = temp;
			}
		}
		return minIndex;
	}
}
