
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractElements {

	public static int[] extractElements(int[] numbers, int n) {
		List<Integer> result = new ArrayList<>();

		int length = numbers.length;
		int start = 0;
		while (start < length) {
			int group = 1;
			for (int j = start + 1; j < length; j++) {
				if (numbers[start] == numbers[j]) {
					group++;
				}
				if (group == n) {
					result.add(numbers[start]);
					start = j;
					break;
				}
			}
			start++;
		}

		int[] resultArray = new int[result.size()];
		int start = 0;
		while (start < result.size()) {
			resultArray[start] = result.get(start);
			start++;
		}
		return resultArray;
	}
}
