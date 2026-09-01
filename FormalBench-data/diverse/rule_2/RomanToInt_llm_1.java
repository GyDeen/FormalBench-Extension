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
		int count = 0;
		while (count < s.length()) {
			if (count < s.length() - 1
					&& romanMap.get(s.charAt(count)) < romanMap.get(s.charAt(count + 1))) {
				result -= romanMap.get(s.charAt(count));
			} else {
				result += romanMap.get(s.charAt(count));
			}
			count++;
		}
		return result;
	}
}
