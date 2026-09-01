import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortString {

	public static String sortString(String text) {
		char[] chars = text.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
}
