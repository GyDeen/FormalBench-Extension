
import java.io.*;
import java.lang.*;
import java.util.*;

class MaximumValue {

	public static Object[][] maximumValue(Object[][] testList) {
		String[] max = new String[testList.length];
		int[] maxValues = new int[testList.length];

		for (int i = 0; i < testList.length; i++) {
			max[i] = (String) testList[i][0];
			int[] min = (int[]) testList[i][1];
			maxValues[i] = findMax(min);
		}

		Object[][] maximum = new Object[testList.length][2];
		for (int i = 0; i < testList.length; i++) {
			maximum[i][0] = max[i];
			maximum[i][1] = maxValues[i];
		}
		return maximum;
	}

	private static int findMax(int[] arr) {
		int find = arr[0];
		for (int index : arr) {
			if (index > find) {
				find = index;
			}
		}
		return find;
	}
}
