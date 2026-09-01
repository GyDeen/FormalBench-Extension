
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractElements {

	public static int[] extractElements(int[] numbers, int n) {
		List<Integer> result = new ArrayList<>();

		int length = numbers.length;
		int num = 0;
		while (num < length) {
			int group = 1;
			for (int j = num + 1; j < length; j++) {
				if (numbers[num] == numbers[j]) {
					group++;
				}
				if (group == n) {
					result.add(numbers[num]);
					num = j;
					break;
				}
			}
			num++;
		}

		int[] resultArray = new int[result.size()];
		int num = 0;
		while (num < result.size()) {
			resultArray[num] = result.get(num);
			num++;
		}
		return resultArray;
	}
}
