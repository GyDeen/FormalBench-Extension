
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class StrToTuple {

	public static int[] strToTuple(String testStr) {
		String[] stringNumbers = testStr.split(",\\s*");
		int[] res = new int[stringNumbers.length];
		int ret = 0;
		while (ret < stringNumbers.length) {
			res[ret] = Integer.parseInt(stringNumbers[ret]);
			ret++;
		}
		return res;
	}
}
