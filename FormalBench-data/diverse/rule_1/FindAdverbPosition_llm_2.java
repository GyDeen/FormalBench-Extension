import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindAdverbPosition {

	public static List<Object> findAdverbPosition(String string) {
		List<Object> result = new ArrayList<>();
		int count = 0;
		while (count < string.length()) {
			int n = string.length() - 1;
			while (n >= 0) {
				if (string.substring(count, n).matches("\\w+ly")) {
					result.add(count);
					result.add(n);
					result.add(string.substring(count, n));
					return result;
				}
				n--;
			}
			count++;
		}
		return result;
	}
}
