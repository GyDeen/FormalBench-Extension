
import java.io.*;
import java.lang.*;
import java.util.*;

class RearExtract {

	public static int[] rearExtract(Object[][] testList) {
		int[] result = new int[testList.length];

		int arr = 0;
		while (arr < testList.length) {
			result[arr] = (Integer) testList[arr][2];
			arr++;
		}

		return result;
	}
}
