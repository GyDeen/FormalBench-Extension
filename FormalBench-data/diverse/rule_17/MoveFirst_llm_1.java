
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveFirst {

	public static int[] moveFirst(int[] testArray) {
		boolean is = testArray == null;
		boolean tmp = testArray.length == 0;
		if (is || tmp) {
			return testArray;
		}

		int[] res = new int[testArray.length];
		res[0] = testArray[testArray.length - 1];

		System.arraycopy(testArray, 0, res, 1, testArray.length - 1);

		return res;
	}
}
