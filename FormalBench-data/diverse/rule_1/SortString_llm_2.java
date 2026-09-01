import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortString {

	public static String sortString(String string) {
		char[] characters = string.toCharArray();
		Arrays.sort(characters);
		return new String(characters);
	}
}
