
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class StrToTuple {

	public static int[] strToTuple(String testStr) {
		String[] stringNumbers = testStr.split(",\\s*");
		int[] res = new int[stringNumbers.length];
		int num = 0;
		while (num < stringNumbers.length) {
			res[num] = Integer.parseInt(stringNumbers[num]);
			num++;
		}
		return res;
	}
}
