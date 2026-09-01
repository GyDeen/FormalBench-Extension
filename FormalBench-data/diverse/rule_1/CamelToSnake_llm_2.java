import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CamelToSnake {

	public static String camelToSnake(String string) {
		String[] result = string.split("(?=[A-Z])");
		StringBuilder builder = new StringBuilder();
		for (String w : result) {
			builder.append(w.toLowerCase());
			builder.append("_");
		}
		return builder.substring(0, builder.length() - 1);
	}
}
