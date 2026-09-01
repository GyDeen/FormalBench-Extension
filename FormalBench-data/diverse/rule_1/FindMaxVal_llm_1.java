import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxVal {

	public static int findMaxVal(int value, int k, int z) {
		int result = 0;
		for (int c = 1; c <= value; c++) {
			if ((c % k) == z) {
				result = c;
			}
		}
		return result;
	}
}
