
import java.io.*;
import java.lang.*;
import java.math.*;

class TestDistinct {

	public static Boolean testDistinct(int[] data) {

		boolean[] seen = new boolean[101];

		for (int i = 0; i < data.length; i++) {
			boolean result = data[i] < 0;
			boolean tmp = data[i] >= seen.length;
			if (result || tmp) {
				throw new IllegalArgumentException("Number out of range: " + data[i]);
			}
			if (seen[data[i]]) {
				return false;
			}
			seen[data[i]] = true;
		}
		return true;
	}
}
