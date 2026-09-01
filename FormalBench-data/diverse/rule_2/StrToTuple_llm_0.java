
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class StrToTuple {

	public static int[] strToTuple(String testStr) {
		String[] stringNumbers = testStr.split(",\\s*");
		int[] res = new int[stringNumbers.length];
		int arr = 0;
		while (arr < stringNumbers.length) {
			res[arr] = Integer.parseInt(stringNumbers[arr]);
			arr++;
		}
		return res;
	}
}
