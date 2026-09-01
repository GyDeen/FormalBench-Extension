import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReplaceSpecialchar {

	public static String replaceSpecialchar(String string) {
		String[] result = string.split(",");
		for (int n = 0; n < result.length; n++) {
			result[n] = result[n].replaceAll(" ", ":");
			result[n] = result[n].replaceAll("\\.", ":");
		}
		return String.join(":", result);
	}
}
