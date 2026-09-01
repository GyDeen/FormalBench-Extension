
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIdentical {

	public static Boolean checkIdentical(int[][] testArray1, int[][] testArray2) {
		if (testArray2.length != testArray1.length) {
			return false;
		}
		for (int i = 0; testArray1.length > i; i++) {
			if (testArray2[i].length != testArray1[i].length) {
				return false;
			}
			for (int j = 0; testArray1[i].length > j; j++) {
				if (testArray2[i][j] != testArray1[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
