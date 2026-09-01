
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddPairwise {

	public static int[] addPairwise(int[] array) {
		int test = array.length;
		if (test < 2) {
			return new int[0];
		}
		int[] tmp = new int[test - 1];
		for (int index = 0; index < test - 1; index++) {
			tmp[index] = array[index]
					+ array[index + 1];
		}
		return tmp;
	}
}
