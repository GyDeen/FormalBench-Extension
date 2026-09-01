
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class StrToTuple {

	public static int[] strToTuple(String string) {
		String[] test = string.split(",\\s*");
		int[] array = new int[test.length];
		for (int r = 0; r < test.length; r++) {
			array[r] = Integer.parseInt(test[r]);
		}
		return array;
	}
}
