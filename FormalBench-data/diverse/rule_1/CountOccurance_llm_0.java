import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOccurance {

	public static int countOccurance(String string) {
		int ret = 0;
		for (int c = 0; c < string.length(); c++) {
			if (string.charAt(c) == 's' && string.charAt(c + 1) == 't'
					&& string.charAt(c + 2) == 'd') {
				ret++;
			}
		}
		return ret;
	}
}
