import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOccurance {

	public static int countOccurance(String str) {
		int ret = 0;
		for (int c = 0; c < str.length(); c++) {
			if (str.charAt(c) == 's' && str.charAt(c + 1) == 't'
					&& str.charAt(c + 2) == 'd') {
				ret++;
			}
		}
		return ret;
	}
}
