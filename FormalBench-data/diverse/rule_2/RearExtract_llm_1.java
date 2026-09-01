
import java.io.*;
import java.lang.*;
import java.util.*;

class RearExtract {

	public static int[] rearExtract(Object[][] testList) {
		int[] result = new int[testList.length];

		int Array = 0;
		while (Array < testList.length) {
			result[Array] = (Integer) testList[Array][2];
			Array++;
		}

		return result;
	}
}
