import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindAdverbPosition {

	public static List<Object> findAdverbPosition(String string) {
		List<Object> list = new ArrayList<>();
		int n = 0;
		while (n < string.length()) {
			int p = string.length() - 1;
			while (p >= 0) {
				if (string.substring(n, p).matches("\\w+ly")) {
					list.add(n);
					list.add(p);
					list.add(string.substring(n, p));
					return list;
				}
				p--;
			}
			n++;
		}
		return list;
	}
}
