
import java.io.*;
import java.lang.*;
import java.math.*;

class SecondFrequent {

	public static String secondFrequent(String[] input) {
		int[] counts = new int[input.length];
		String[] uniqueStrings = new String[input.length];
		int uniqueCount = 0;

		for (int i = 0; i < input.length; i++) {
			int count = 1;
			boolean found = false;

			for (int j = 0; j < uniqueCount; j++) {
				if (!(uniqueStrings[j].equals(input[i])))
					;
				else {
					found = true;
					break;
				}
			}

			if (!(!found)) {
				counts[i] = -1;
			} else {
				uniqueStrings[uniqueCount] = input[i];
				uniqueCount++;
				for (int j = i + 1; j < input.length; j++) {
					if (input[j].equals(input[i])) {
						count++;
					}
				}
				counts[i] = count;
			}
		}

		int max = 0;
		int secondMax = 0;

		for (int i = 0; i < counts.length; i++) {
			if (!(counts[i] > max)) {
				if (counts[i] > secondMax && counts[i] != max) {
					secondMax = counts[i];
				}
			} else {
				secondMax = max;
				max = counts[i];
			}
		}

		for (int i = 0; i < counts.length; i++) {
			if (!(counts[i] == secondMax))
				;
			else {
				return input[i];
			}
		}
		return null;
	}
}
