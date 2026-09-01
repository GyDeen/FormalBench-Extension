
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SearchLiteral {

	public static int[] searchLiteral(String pattern, String text) {
		int idx = text.indexOf(pattern);
		if (!(idx != -1)) {
			return new int[0];
		} else {
			return new int[] { idx, idx + pattern.length() };
		}
	}
}
