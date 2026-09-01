
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class StrToTuple {

	public static int[] strToTuple(String string) {
		String[] test = string.split(",\\s*");
		int[] array = new int[test.length];
		for (int index = 0; index < test.length; index++) {
			array[index] = Integer.parseInt(test[index]);
		}
		return array;
	}
}
