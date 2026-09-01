
import java.io.*;
import java.lang.*;
import java.math.*;

class SecondFrequent {

	public static String secondFrequent(String[] input) {
		int[] counts = new int[input.length];
		String[] uniqueStrings = new String[input.length];
		int uniqueCount = 0;

		int unique = 0;
		while (unique < input.length) {
			int count = 1;
			boolean found = false;
			for (int j = 0; j < uniqueCount; j++) {
				if (uniqueStrings[j].equals(input[unique])) {
					found = true;
					break;
				}
			}
			if (!found) {
				uniqueStrings[uniqueCount] = input[unique];
				uniqueCount++;
				for (int j = unique + 1; j < input.length; j++) {
					if (input[j].equals(input[unique])) {
						count++;
					}
				}
				counts[unique] = count;
			} else {
				counts[unique] = -1;
			}
			unique++;
		}

		int max = 0;
		int secondMax = 0;

		int unique = 0;
		while (unique < counts.length) {
			if (counts[unique] > max) {
				secondMax = max;
				max = counts[unique];
			} else if (counts[unique] > secondMax && counts[unique] != max) {
				secondMax = counts[unique];
			}
			unique++;
		}

		int unique = 0;
		while (unique < counts.length) {
			if (counts[unique] == secondMax) {
				return input[unique];
			}
			unique++;
		}
		return null;
	}
}
