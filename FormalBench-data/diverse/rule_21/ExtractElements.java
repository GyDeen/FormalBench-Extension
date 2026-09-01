
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractElements {

	public static int[] extractElements(int[] numbers, int n) {
		List<Integer> result = new ArrayList<>();

		int length = numbers.length;
		for (int i = 0; i < length; i++) {
			int group = 1;
			for (int j = i + 1; j < length; j++) {
				if (numbers[j] == numbers[i]) {
					group++;
				}
				if (n == group) {
					result.add(numbers[i]);
					i = j;
					break;
				}
			}
		}

		int[] resultArray = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			resultArray[i] = result.get(i);
		}
		return resultArray;
	}
}
