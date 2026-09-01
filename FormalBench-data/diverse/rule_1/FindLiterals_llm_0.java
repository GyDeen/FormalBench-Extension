
import java.io.*;
import java.lang.*;
import java.util.*;

class FindLiterals {

	public static Object[] findLiterals(String string, String regex) {
		List<Object> list = new ArrayList<>();
		int index = 0;
		while (index < string.length()) {
			int i = string.indexOf(regex, index);
			if (i == -1) {
				break;
			}
			int count = i + regex.length();
			list.add(regex);
			list.add(i);
			list.add(count);
			index = count;
		}

		return list.toArray(new Object[list.size()]);
	}
}
