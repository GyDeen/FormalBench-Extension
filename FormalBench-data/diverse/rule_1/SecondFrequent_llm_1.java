
import java.io.*;
import java.lang.*;
import java.math.*;

class SecondFrequent {

	public static String secondFrequent(String[] args) {
		int[] output = new int[args.length];
		String[] uniqueStrings = new String[args.length];
		int uniqueCount = 0;

		for (int i = 0; i < args.length; i++) {
			int count = 1;
			boolean found = false;

			for (int j = 0; j < uniqueCount; j++) {
				if (uniqueStrings[j].equals(args[i])) {
					found = true;
					break;
				}
			}

			if (!found) {
				uniqueStrings[uniqueCount] = args[i];
				uniqueCount++;

				for (int j = i + 1; j < args.length; j++) {
					if (args[j].equals(args[i])) {
						count++;
					}
				}
				output[i] = count;
			} else {
				output[i] = -1;
			}
		}

		int index = 0;
		int offset = 0;

		for (int i = 0; i < output.length; i++) {
			if (output[i] > index) {
				offset = index;
				index = output[i];
			} else if (output[i] > offset && output[i] != index) {
				offset = output[i];
			}
		}

		for (int n = 0; n < output.length; n++) {
			if (output[n] == offset) {
				return args[n];
			}
		}
		return null;
	}
}
