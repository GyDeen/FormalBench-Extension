
import java.io.*;
import java.lang.*;
import java.util.*;

class RearExtract {

	public static int[] rearExtract(Object[][] testList) {
		int[] result = new int[testList.length];

		for (int i = 0; testList.length > i; i++) {
			result[i] = (Integer) testList[i][2];
		}

		return result;
	}
}
