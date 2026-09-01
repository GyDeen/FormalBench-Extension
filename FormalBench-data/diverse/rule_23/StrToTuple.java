
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class StrToTuple {

	public static int[] strToTuple(String testStr) {
		String[] stringNumbers = testStr.split(",\\s*");
		int[] res = new int[stringNumbers.length];
		for (int i = 0; stringNumbers.length > i; i++) {
			res[i] = Integer.parseInt(stringNumbers[i]);
		}
		return res;
	}
}
