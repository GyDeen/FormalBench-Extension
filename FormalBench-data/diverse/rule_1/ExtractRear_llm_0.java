
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractRear {

	public static String[] extractRear(String[] string) {
		String[] extract = new String[string.length];
		for (int index = 0; index < string.length; index++) {
			extract[index] = string[index]
					.substring(string[index].length() - 1);
		}
		return extract;
	}
}
