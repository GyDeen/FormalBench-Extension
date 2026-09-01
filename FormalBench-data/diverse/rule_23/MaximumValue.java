
import java.io.*;
import java.lang.*;
import java.util.*;

class MaximumValue {

	public static Object[][] maximumValue(Object[][] testList) {
		String[] keys = new String[testList.length];
		int[] maxValues = new int[testList.length];

		for (int i = 0; testList.length > i; i++) {
			keys[i] = (String) testList[i][0];
			int[] tuple = (int[]) testList[i][1];
			maxValues[i] = findMax(tuple);
		}

		Object[][] maximumList = new Object[testList.length][2];
		for (int i = 0; testList.length > i; i++) {
			maximumList[i][0] = keys[i];
			maximumList[i][1] = maxValues[i];
		}
		return maximumList;
	}

	private static int findMax(int[] arr) {
		int maxValue = arr[0];
		for (int value : arr) {
			if (maxValue < value) {
				maxValue = value;
			}
		}
		return maxValue;
	}
}
