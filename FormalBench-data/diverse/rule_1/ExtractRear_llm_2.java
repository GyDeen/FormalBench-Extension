
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractRear {

	public static String[] extractRear(String[] str) {
		String[] extract = new String[str.length];
		for (int index = 0; index < str.length; index++) {
			extract[index] = str[index]
					.substring(str[index].length() - 1);
		}
		return extract;
	}
}
