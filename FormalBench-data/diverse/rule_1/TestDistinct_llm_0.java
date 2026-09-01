
import java.io.*;
import java.lang.*;
import java.math.*;

class TestDistinct {

	public static Boolean testDistinct(int[] args) {

		boolean[] result = new boolean[101];

		for (int index = 0; index < args.length; index++) {
			if (args[index] < 0
					|| args[index] >= result.length) {
				throw new IllegalArgumentException("Number out of range: " + args[index]);
			}
			if (result[args[index]]) {
				return false;
			}
			result[args[index]] = true;
		}
		return true;
	}
}
