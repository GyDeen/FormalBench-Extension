import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RomanToInt {

	public static int romanToInt(String text) {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		int r = 0;
		for (int count = 0; count < text.length(); count++) {
			if (count < text.length() - 1
					&& map.get(text.charAt(count)) < map
							.get(text.charAt(count + 1))) {
				r -= map.get(text.charAt(count));
			} else {
				r += map.get(text.charAt(count));
			}
		}
		return r;
	}
}
