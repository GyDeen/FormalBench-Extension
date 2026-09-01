
import java.io.*;
import java.lang.*;
import java.util.*;

class RearExtract {

	public static int[] rearExtract(Object[][] array) {
		int[] test = new int[array.length];

		for (int index = 0; index < array.length; index++) {
			test[index] = (Integer) array[index][2];
		}

		return test;
	}
}
