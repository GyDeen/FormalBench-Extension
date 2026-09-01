import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOccurance {

	public static int countOccurance(String string) {
		int ret = 0;
		for (int index = 0; index < string.length(); index++) {
			if (string.charAt(index) == 's' && string.charAt(index + 1) == 't'
					&& string.charAt(index + 2) == 'd') {
				ret++;
			}
		}
		return ret;
	}
}
