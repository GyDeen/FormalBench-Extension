
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIdentical {

	public static Boolean checkIdentical(int[][] testArray1, int[][] testArray2) {
		if (testArray1.length != testArray2.length) {
			return false;
		}
		int num = 0;
		while (num < testArray1.length) {
			if (testArray1[num].length != testArray2[num].length) {
				return false;
			}
			for (int j = 0; j < testArray1[num].length; j++) {
				if (testArray1[num][j] != testArray2[num][j]) {
					return false;
				}
			}
			num++;
		}
		return true;
	}
}
