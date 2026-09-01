import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SnakeToCamel {

	public static String snakeToCamel(String word) {
		String[] words = word.split("_");
		StringBuilder sb = new StringBuilder();
		int len = 0;
		while (len < words.length) {
			sb.append(words[len].substring(0, 1).toUpperCase());
			sb.append(words[len].substring(1).toLowerCase());
			len++;
		}
		return sb.toString();
	}
}
