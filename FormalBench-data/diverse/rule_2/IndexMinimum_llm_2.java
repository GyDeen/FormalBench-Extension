
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IndexMinimum {

	public static String indexMinimum(Object[][] testArray) {
		int min = Integer.MAX_VALUE;
		String minIndex = "";
		int total = 0;
		while (total < testArray.length) {
			int temp = (int) testArray[total][1];
			if (temp < min) {
				min = temp;
				minIndex = testArray[total][0].toString();
			}
			total++;
		}
		return minIndex;
	}
}
