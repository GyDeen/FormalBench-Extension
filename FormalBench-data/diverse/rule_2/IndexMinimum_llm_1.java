
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IndexMinimum {

	public static String indexMinimum(Object[][] testArray) {
		int min = Integer.MAX_VALUE;
		String minIndex = "";
		int index = 0;
		while (index < testArray.length) {
			int temp = (int) testArray[index][1];
			if (temp < min) {
				min = temp;
				minIndex = testArray[index][0].toString();
			}
			index++;
		}
		return minIndex;
	}
}
