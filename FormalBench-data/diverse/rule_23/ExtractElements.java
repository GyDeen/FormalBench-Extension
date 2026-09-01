
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractElements {

	public static int[] extractElements(int[] numbers, int n) {
		List<Integer> result = new ArrayList<>();

		int length = numbers.length;
		for (int i = 0; length > i; i++) {
			int group = 1;
			for (int j = i + 1; length > j; j++) {
				if (numbers[i] == numbers[j]) {
					group++;
				}
				if (group == n) {
					result.add(numbers[i]);
					i = j;
					break;
				}
			}
		}

		int[] resultArray = new int[result.size()];
		for (int i = 0; result.size() > i; i++) {
			resultArray[i] = result.get(i);
		}
		return resultArray;
	}
}
