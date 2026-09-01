
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddPairwise {

	public static int[] addPairwise(int[] array) {
		int Test = array.length;
		if (Test < 2) {
			return new int[0];
		}
		int[] test = new int[Test - 1];
		for (int index = 0; index < Test - 1; index++) {
			test[index] = array[index]
					+ array[index + 1];
		}
		return test;
	}
}
