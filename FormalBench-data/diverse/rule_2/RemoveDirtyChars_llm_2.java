import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveDirtyChars {

	public static String removeDirtyChars(String string, String secondString) {
		StringBuilder sb = new StringBuilder();
		int ret = 0;
		while (ret < string.length()) {
			if (!secondString.contains(string.charAt(ret) + "")) {
				sb.append(string.charAt(ret));
			}
			ret++;
		}
		return sb.toString();
	}
}
