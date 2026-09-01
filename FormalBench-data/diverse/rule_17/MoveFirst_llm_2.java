
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveFirst {

	public static int[] moveFirst(int[] testArray) {
		boolean result = testArray == null;
		boolean ret = testArray.length == 0;
		if (result || ret) {
			return testArray;
		}

		int[] res = new int[testArray.length];
		res[0] = testArray[testArray.length - 1];

		System.arraycopy(testArray, 0, res, 1, testArray.length - 1);

		return res;
	}
}
