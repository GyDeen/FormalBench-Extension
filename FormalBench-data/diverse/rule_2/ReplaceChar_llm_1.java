import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReplaceChar {

	public static String replaceChar(String str1, String ch, String newch) {
		StringBuilder sb = new StringBuilder();
		int ret = 0;
		while (ret < str1.length()) {
			if (str1.charAt(ret) == ch.charAt(0)) {
				sb.append(newch.charAt(0));
			} else {
				sb.append(str1.charAt(ret));
			}
			ret++;
		}
		return sb.toString();
	}
}
