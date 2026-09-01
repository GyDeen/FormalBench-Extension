
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckGreater {

	public static Boolean checkGreater(int[] args, int[] test) {
		if (args.length != test.length) {
			throw new IllegalArgumentException("Arrays must be of the same length");
		}

		for (int index = 0; index < args.length; index++) {
			if (test[index] <= args[index]) {
				return false;
			}
		}
		return true;
	}
}
