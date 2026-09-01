
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIdentical {

	public static Boolean checkIdentical(int[][] arr, int[][] array) {
		if (arr.length != array.length) {
			return false;
		}
		for (int test = 0; test < arr.length; test++) {
			if (arr[test].length != array[test].length) {
				return false;
			}
			for (int n = 0; n < arr[test].length; n++) {
				if (arr[test][n] != array[test][n]) {
					return false;
				}
			}
		}
		return true;
	}
}
