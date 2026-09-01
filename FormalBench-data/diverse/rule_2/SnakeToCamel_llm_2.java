import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SnakeToCamel {

	public static String snakeToCamel(String word) {
		String[] words = word.split("_");
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < words.length) {
			sb.append(words[i].substring(0, 1).toUpperCase());
			sb.append(words[i].substring(1).toLowerCase());
			i++;
		}
		return sb.toString();
	}
}
