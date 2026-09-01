import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SnakeToCamel {

	public static String snakeToCamel(String word) {
		String[] words = word.split("_");
		StringBuilder sb = new StringBuilder();
		int n = 0;
		while (n < words.length) {
			sb.append(words[n].substring(0, 1).toUpperCase());
			sb.append(words[n].substring(1).toLowerCase());
			n++;
		}
		return sb.toString();
	}
}
