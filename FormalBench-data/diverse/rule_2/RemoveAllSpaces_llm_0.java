import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveAllSpaces {

	public static String removeAllSpaces(String text) {
		StringBuilder sb = new StringBuilder();
		int ret = 0;
		while (ret < text.length()) {
			if (text.charAt(ret) != ' ') {
				sb.append(text.charAt(ret));
			}
			ret++;
		}
		return sb.toString();
	}
}
