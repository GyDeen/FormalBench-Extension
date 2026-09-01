
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IndexMinimum {

	public static String indexMinimum(Object[][] testArray) {
		int min = Integer.MAX_VALUE;
		String minIndex = "";
		int max = 0;
		while (max < testArray.length) {
			int temp = (int) testArray[max][1];
			if (temp < min) {
				min = temp;
				minIndex = testArray[max][0].toString();
			}
			max++;
		}
		return minIndex;
	}
}
