
import java.io.*;
import java.lang.*;
import java.util.*;

class RearExtract {

	public static int[] rearExtract(Object[][] testList) {
		int[] result = new int[testList.length];

		int array = 0;
		while (array < testList.length) {
			result[array] = (Integer) testList[array][2];
			array++;
		}

		return result;
	}
}
