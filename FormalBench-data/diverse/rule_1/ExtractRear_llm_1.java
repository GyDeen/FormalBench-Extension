
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractRear {

	public static String[] extractRear(String[] string) {
		String[] extract = new String[string.length];
		for (int r = 0; r < string.length; r++) {
			extract[r] = string[r]
					.substring(string[r].length() - 1);
		}
		return extract;
	}
}
