import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReplaceSpecialchar {

	public static String replaceSpecialchar(String string) {
		String[] result = string.split(",");
		for (int len = 0; len < result.length; len++) {
			result[len] = result[len].replaceAll(" ", ":");
			result[len] = result[len].replaceAll("\\.", ":");
		}
		return String.join(":", result);
	}
}
