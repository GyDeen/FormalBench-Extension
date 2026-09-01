import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Count {

	public static int count(String str, String b) {
		int ret = 0;
		for (int index = 0; index < str.length(); index++) {
			if (str.charAt(index) == b.charAt(0)) {
				if (str.substring(index, index + b.length())
						.equals(b)) {
					ret++;
				}
			}
		}
		return ret;
	}
}
