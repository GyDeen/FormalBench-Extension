
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckSmaller {

	public static Boolean checkSmaller(int[] args, int[] test) {
		if (args.length != test.length) {
			throw new IllegalArgumentException("Arrays must have the same length.");
		}

		for (int index = 0; index < test.length; index++) {
			if (args[index] > test[index]) {
				continue;
			} else if (args[index] <= test[index]) {
				return false;
			}
		}
		return true;
	}
}
