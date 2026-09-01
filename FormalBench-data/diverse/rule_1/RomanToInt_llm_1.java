import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RomanToInt {

	public static int romanToInt(String string) {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		int r = 0;
		for (int c = 0; c < string.length(); c++) {
			if (c < string.length() - 1
					&& map.get(string.charAt(c)) < map
							.get(string.charAt(c + 1))) {
				r -= map.get(string.charAt(c));
			} else {
				r += map.get(string.charAt(c));
			}
		}
		return r;
	}
}
