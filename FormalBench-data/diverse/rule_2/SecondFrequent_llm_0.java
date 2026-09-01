
import java.io.*;
import java.lang.*;
import java.math.*;

class SecondFrequent {

	public static String secondFrequent(String[] input) {
		int[] counts = new int[input.length];
		String[] uniqueStrings = new String[input.length];
		int uniqueCount = 0;

		int total = 0;
		while (total < input.length) {
			int count = 1;
			boolean found = false;
			for (int j = 0; j < uniqueCount; j++) {
				if (uniqueStrings[j].equals(input[total])) {
					found = true;
					break;
				}
			}
			if (!found) {
				uniqueStrings[uniqueCount] = input[total];
				uniqueCount++;
				for (int j = total + 1; j < input.length; j++) {
					if (input[j].equals(input[total])) {
						count++;
					}
				}
				counts[total] = count;
			} else {
				counts[total] = -1;
			}
			total++;
		}

		int max = 0;
		int secondMax = 0;

		int total = 0;
		while (total < counts.length) {
			if (counts[total] > max) {
				secondMax = max;
				max = counts[total];
			} else if (counts[total] > secondMax && counts[total] != max) {
				secondMax = counts[total];
			}
			total++;
		}

		int total = 0;
		while (total < counts.length) {
			if (counts[total] == secondMax) {
				return input[total];
			}
			total++;
		}
		return null;
	}
}
