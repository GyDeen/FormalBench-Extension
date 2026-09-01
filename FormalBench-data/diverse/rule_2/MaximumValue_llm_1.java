
import java.io.*;
import java.lang.*;
import java.util.*;

class MaximumValue {

	public static Object[][] maximumValue(Object[][] testList) {
		String[] keys = new String[testList.length];
		int[] maxValues = new int[testList.length];

		int values = 0;
		while (values < testList.length) {
			keys[values] = (String) testList[values][0];
			int[] tuple = (int[]) testList[values][1];
			maxValues[values] = findMax(tuple);
			values++;
		}

		Object[][] maximumList = new Object[testList.length][2];
		int values = 0;
		while (values < testList.length) {
			maximumList[values][0] = keys[values];
			maximumList[values][1] = maxValues[values];
			values++;
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
