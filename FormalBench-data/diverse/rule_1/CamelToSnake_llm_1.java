import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CamelToSnake {

	public static String camelToSnake(String name) {
		String[] result = name.split("(?=[A-Z])");
		StringBuilder builder = new StringBuilder();
		for (String string : result) {
			builder.append(string.toLowerCase());
			builder.append("_");
		}
		return builder.substring(0, builder.length() - 1);
	}
}
