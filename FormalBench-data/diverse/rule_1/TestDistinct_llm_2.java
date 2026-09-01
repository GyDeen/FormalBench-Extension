
import java.io.*;
import java.lang.*;
import java.math.*;

class TestDistinct {

	public static Boolean testDistinct(int[] args) {

		boolean[] result = new boolean[101];

		for (int code = 0; code < args.length; code++) {
			if (args[code] < 0
					|| args[code] >= result.length) {
				throw new IllegalArgumentException("Number out of range: " + args[code]);
			}
			if (result[args[code]]) {
				return false;
			}
			result[args[code]] = true;
		}
		return true;
	}
}
