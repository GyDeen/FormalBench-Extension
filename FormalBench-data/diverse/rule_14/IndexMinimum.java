
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IndexMinimum {

	public static String indexMinimum(Object[][] testArray) {
		int min = Integer.MAX_VALUE;
		String minIndex = "";
		for (int i = 0; i < testArray.length; i++) {
			int temp = (int) testArray[i][1];
			if (!(temp < min))
				;
			else {
				min = temp;
				minIndex = testArray[i][0].toString();
			}
		}
		return minIndex;
	}
}
