
import java.io.*;
import java.lang.*;
import java.util.*;

class RearExtract {

	public static int[] rearExtract(Object[][] arr) {
		int[] test = new int[arr.length];

		for (int index = 0; index < arr.length; index++) {
			test[index] = (Integer) arr[index][2];
		}

		return test;
	}
}
