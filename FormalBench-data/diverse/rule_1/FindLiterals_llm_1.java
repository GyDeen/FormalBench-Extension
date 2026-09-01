
import java.io.*;
import java.lang.*;
import java.util.*;

class FindLiterals {

	public static Object[] findLiterals(String string, String regex) {
		List<Object> ret = new ArrayList<>();
		int index = 0;
		while (index < string.length()) {
			int i = string.indexOf(regex, index);
			if (i == -1) {
				break;
			}
			int count = i + regex.length();
			ret.add(regex);
			ret.add(i);
			ret.add(count);
			index = count;
		}

		return ret.toArray(new Object[ret.size()]);
	}
}
