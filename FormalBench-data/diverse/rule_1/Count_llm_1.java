import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Count {

	public static int count(String string, String u) {
		int ret = 0;
		for (int index = 0; index < string.length(); index++) {
			if (string.charAt(index) == u.charAt(0)) {
				if (string.substring(index, index + u.length())
						.equals(u)) {
					ret++;
				}
			}
		}
		return ret;
	}
}
