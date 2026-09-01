import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindAdverbPosition {

	public static List<Object> findAdverbPosition(String string) {
		List<Object> list = new ArrayList<>();
		int count = 0;
		while (count < string.length()) {
			int n = string.length() - 1;
			while (n >= 0) {
				if (string.substring(count, n).matches("\\w+ly")) {
					list.add(count);
					list.add(n);
					list.add(string.substring(count, n));
					return list;
				}
				n--;
			}
			count++;
		}
		return list;
	}
}
