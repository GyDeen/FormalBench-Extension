
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIdentical {

	public static Boolean checkIdentical(int[][] array, int[][] Test) {
		if (array.length != Test.length) {
			return false;
		}
		for (int test = 0; test < array.length; test++) {
			if (array[test].length != Test[test].length) {
				return false;
			}
			for (int n = 0; n < array[test].length; n++) {
				if (array[test][n] != Test[test][n]) {
					return false;
				}
			}
		}
		return true;
	}
}
