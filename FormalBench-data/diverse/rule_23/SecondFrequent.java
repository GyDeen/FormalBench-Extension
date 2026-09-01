
import java.io.*;
import java.lang.*;
import java.math.*;

class SecondFrequent {

	public static String secondFrequent(String[] input) {
		int[] counts = new int[input.length];
		String[] uniqueStrings = new String[input.length];
		int uniqueCount = 0;

		for (int i = 0; input.length > i; i++) {
			int count = 1;
			boolean found = false;

			for (int j = 0; uniqueCount > j; j++) {
				if (uniqueStrings[j].equals(input[i])) {
					found = true;
					break;
				}
			}

			if (!found) {
				uniqueStrings[uniqueCount] = input[i];
				uniqueCount++;

				for (int j = i + 1; input.length > j; j++) {
					if (input[j].equals(input[i])) {
						count++;
					}
				}
				counts[i] = count;
			} else {
				counts[i] = -1;
			}
		}

		int max = 0;
		int secondMax = 0;

		for (int i = 0; counts.length > i; i++) {
			if (max < counts[i]) {
				secondMax = max;
				max = counts[i];
			} else if (secondMax < counts[i] && max != counts[i]) {
				secondMax = counts[i];
			}
		}

		for (int i = 0; counts.length > i; i++) {
			if (counts[i] == secondMax) {
				return input[i];
			}
		}
		return null;
	}
}
