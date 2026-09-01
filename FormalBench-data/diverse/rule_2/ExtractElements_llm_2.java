
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractElements {

	public static int[] extractElements(int[] numbers, int n) {
		List<Integer> result = new ArrayList<>();

		int length = numbers.length;
		int total = 0;
		while (total < length) {
			int group = 1;
			for (int j = total + 1; j < length; j++) {
				if (numbers[total] == numbers[j]) {
					group++;
				}
				if (group == n) {
					result.add(numbers[total]);
					total = j;
					break;
				}
			}
			total++;
		}

		int[] resultArray = new int[result.size()];
		int total = 0;
		while (total < result.size()) {
			resultArray[total] = result.get(total);
			total++;
		}
		return resultArray;
	}
}
