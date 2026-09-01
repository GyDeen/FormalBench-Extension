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
		int total = 0;
		while (total < s.length()) {
			if (total < s.length() - 1
					&& romanMap.get(s.charAt(total)) < romanMap.get(s.charAt(total + 1))) {
				result -= romanMap.get(s.charAt(total));
			} else {
				result += romanMap.get(s.charAt(total));
			}
			total++;
		}
		return result;
	}
}
