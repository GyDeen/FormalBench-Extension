
import java.io.*;
import java.lang.*;
import java.util.*;

class MaximumValue {

	public static Object[][] maximumValue(Object[][] testList) {
		String[] keys = new String[testList.length];
		int[] maxValues = new int[testList.length];

		int max = 0;
		while (max < testList.length) {
			keys[max] = (String) testList[max][0];
			int[] tuple = (int[]) testList[max][1];
			maxValues[max] = findMax(tuple);
			max++;
		}

		Object[][] maximumList = new Object[testList.length][2];
		int max = 0;
		while (max < testList.length) {
			maximumList[max][0] = keys[max];
			maximumList[max][1] = maxValues[max];
			max++;
		}
		return maximumList;
	}

	private static int findMax(int[] arr) {
		int maxValue = arr[0];
		for (int value : arr) {
			if (value > maxValue) {
				maxValue = value;
			}
		}
		return maxValue;
	}
}
