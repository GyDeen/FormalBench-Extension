import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RomanToInt {

	public static int romanToInt(String s) {
		Map<Character, Integer> romanMap = new HashMap<>();
		romanMap.put('I', 1);
		romanMap.put('V', 5);
		romanMap.put('X', 10);
		romanMap.put('L', 50);
		romanMap.put('C', 100);
		romanMap.put('D', 500);
		romanMap.put('M', 1000);
		int result = 0;
		int i = 0;
		while (i < s.length()) {
			if (i < s.length() - 1
					&& romanMap.get(s.charAt(i)) < romanMap.get(s.charAt(i + 1))) {
				result -= romanMap.get(s.charAt(i));
			} else {
				result += romanMap.get(s.charAt(i));
			}
			i++;
		}
		return result;
	}
}
