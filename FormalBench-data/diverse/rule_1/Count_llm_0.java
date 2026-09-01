import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Count {

	public static int count(String string, String b) {
		int ret = 0;
		for (int index = 0; index < string.length(); index++) {
			if (string.charAt(index) == b.charAt(0)) {
				if (string.substring(index, index + b.length())
						.equals(b)) {
					ret++;
				}
			}
		}
		return ret;
	}
}
