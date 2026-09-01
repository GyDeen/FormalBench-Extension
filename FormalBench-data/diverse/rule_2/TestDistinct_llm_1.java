
import java.io.*;
import java.lang.*;
import java.math.*;

class TestDistinct {

	public static Boolean testDistinct(int[] data) {

		boolean[] seen = new boolean[101];

		int index = 0;
		while (index < data.length) {
			if (data[index] < 0 || data[index] >= seen.length) {
				throw new IllegalArgumentException("Number out of range: " + data[index]);
			}
			if (seen[data[index]]) {
				return false;
			}
			seen[data[index]] = true;
			index++;
		}
		return true;
	}
}
