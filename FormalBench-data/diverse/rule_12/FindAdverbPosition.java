import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindAdverbPosition {

	public static List<Object> findAdverbPosition(String text) {
		int i = 0;
		List<Object> res = new ArrayList<>();
		while (i < text.length()) {
			int j = text.length() - 1;
			while (j >= 0) {
				if (text.substring(i, j).matches("\\w+ly")) {
					res.add(i);
					res.add(j);
					res.add(text.substring(i, j));
					return res;
				}
				j--;
			}
			i++;
		}
		return res;
	}
}
