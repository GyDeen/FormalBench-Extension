
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIdentical {

	public static Boolean checkIdentical(int[][] testArray1, int[][] testArray2) {
		if (testArray1.length != testArray2.length) {
			return false;
		}
		int test = 0;
		while (test < testArray1.length) {
			if (testArray1[test].length != testArray2[test].length) {
				return false;
			}
			for (int j = 0; j < testArray1[test].length; j++) {
				if (testArray1[test][j] != testArray2[test][j]) {
					return false;
				}
			}
			test++;
		}
		return true;
	}
}
