
import java.io.*;
import java.lang.*;
import java.util.*;

class MaximumValue {

	public static Object[][] maximumValue(Object[][] testList) {
		String[] keys = new String[testList.length];
		int[] maxValues = new int[testList.length];

		int maximum = 0;
		while (maximum < testList.length) {
			keys[maximum] = (String) testList[maximum][0];
			int[] tuple = (int[]) testList[maximum][1];
			maxValues[maximum] = findMax(tuple);
			maximum++;
		}

		Object[][] maximumList = new Object[testList.length][2];
		int maximum = 0;
		while (maximum < testList.length) {
			maximumList[maximum][0] = keys[maximum];
			maximumList[maximum][1] = maxValues[maximum];
			maximum++;
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
