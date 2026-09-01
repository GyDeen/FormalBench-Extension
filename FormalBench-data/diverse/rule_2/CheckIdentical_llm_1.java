
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIdentical {

	public static Boolean checkIdentical(int[][] testArray1, int[][] testArray2) {
		if (testArray1.length != testArray2.length) {
			return false;
		}
		int index = 0;
		while (index < testArray1.length) {
			if (testArray1[index].length != testArray2[index].length) {
				return false;
			}
			for (int j = 0; j < testArray1[index].length; j++) {
				if (testArray1[index][j] != testArray2[index][j]) {
					return false;
				}
			}
			index++;
		}
		return true;
	}
}
