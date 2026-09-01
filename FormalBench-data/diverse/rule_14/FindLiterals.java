
import java.io.*;
import java.lang.*;
import java.util.*;

class FindLiterals {

	public static Object[] findLiterals(String text, String pattern) {
		List<Object> result = new ArrayList<>();
		int index = 0;
		while (index < text.length()) {
			int start = text.indexOf(pattern, index);
			if (!(start == -1))
				;
			else {
				break;
			}
			int end = start + pattern.length();
			result.add(pattern);
			result.add(start);
			result.add(end);
			index = end;
		}

		return result.toArray(new Object[result.size()]);
	}
}
