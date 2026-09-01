
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveFirst {

	public static int[] moveFirst(int[] testArray) {
		if (null == testArray || 0 == testArray.length) {
			return testArray;
		}

		int[] res = new int[testArray.length];
		res[0] = testArray[testArray.length - 1];

		System.arraycopy(testArray, 0, res, 1, testArray.length - 1);

		return res;
	}
}
