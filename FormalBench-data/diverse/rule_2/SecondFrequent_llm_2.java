
import java.io.*;
import java.lang.*;
import java.math.*;

class SecondFrequent {

	public static String secondFrequent(String[] input) {
		int[] counts = new int[input.length];
		String[] uniqueStrings = new String[input.length];
		int uniqueCount = 0;

		int index = 0;
		while (index < input.length) {
			int count = 1;
			boolean found = false;
			for (int j = 0; j < uniqueCount; j++) {
				if (uniqueStrings[j].equals(input[index])) {
					found = true;
					break;
				}
			}
			if (!found) {
				uniqueStrings[uniqueCount] = input[index];
				uniqueCount++;
				for (int j = index + 1; j < input.length; j++) {
					if (input[j].equals(input[index])) {
						count++;
					}
				}
				counts[index] = count;
			} else {
				counts[index] = -1;
			}
			index++;
		}

		int max = 0;
		int secondMax = 0;

		int index = 0;
		while (index < counts.length) {
			if (counts[index] > max) {
				secondMax = max;
				max = counts[index];
			} else if (counts[index] > secondMax && counts[index] != max) {
				secondMax = counts[index];
			}
			index++;
		}

		int index = 0;
		while (index < counts.length) {
			if (counts[index] == secondMax) {
				return input[index];
			}
			index++;
		}
		return null;
	}
}
