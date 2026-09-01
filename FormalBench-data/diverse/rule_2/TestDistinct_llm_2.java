
import java.io.*;
import java.lang.*;
import java.math.*;

class TestDistinct {

	public static Boolean testDistinct(int[] data) {

		boolean[] seen = new boolean[101];

		int test = 0;
		while (test < data.length) {
			if (data[test] < 0 || data[test] >= seen.length) {
				throw new IllegalArgumentException("Number out of range: " + data[test]);
			}
			if (seen[data[test]]) {
				return false;
			}
			seen[data[test]] = true;
			test++;
		}
		return true;
	}
}
