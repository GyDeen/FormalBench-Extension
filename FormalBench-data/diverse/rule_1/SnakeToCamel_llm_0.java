import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SnakeToCamel {

	public static String snakeToCamel(String string) {
		String[] result = string.split("_");
		StringBuilder builder = new StringBuilder();
		for (int n = 0; n < result.length; n++) {
			builder.append(result[n].substring(0, 1).toUpperCase());
			builder.append(result[n].substring(1).toLowerCase());
		}
		return builder.toString();
	}
}
