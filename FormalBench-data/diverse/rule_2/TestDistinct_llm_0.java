
import java.io.*;
import java.lang.*;
import java.math.*;

class TestDistinct {

	public static Boolean testDistinct(int[] data) {

		boolean[] seen = new boolean[101];

		int num = 0;
		while (num < data.length) {
			if (data[num] < 0 || data[num] >= seen.length) {
				throw new IllegalArgumentException("Number out of range: " + data[num]);
			}
			if (seen[data[num]]) {
				return false;
			}
			seen[data[num]] = true;
			num++;
		}
		return true;
	}
}
