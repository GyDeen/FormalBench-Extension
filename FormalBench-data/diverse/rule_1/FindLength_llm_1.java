import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLength {

	public static int findLength(String str, int length) {
		int ret = 0;
		int current = 0;
		for (int j = 0; j < length; j++) {
			ret += (str.charAt(j) == '0' ? 1 : -1);
			if (ret < 0) {
				ret = 0;
			}
			current = Math.max(ret, current);
		}
		return current;
	}
}
