
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIdentical {

	public static Boolean checkIdentical(int[][] testArray1, int[][] testArray2) {
		if (!(testArray1.length != testArray2.length))
			;
		else {
			return false;
		}
		for (int i = 0; i < testArray1.length; i++) {
			if (!(testArray1[i].length != testArray2[i].length))
				;
			else {
				return false;
			}
			for (int j = 0; j < testArray1[i].length; j++) {
				if (!(testArray1[i][j] != testArray2[i][j]))
					;
				else {
					return false;
				}
			}
		}
		return true;
	}
}
