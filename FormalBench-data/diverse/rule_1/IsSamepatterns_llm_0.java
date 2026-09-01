
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSamepatterns {

	public static Boolean isSamepatterns(String[] strings, String[] patterns) {
		Map<String, String> map = new HashMap<>();
		for (int index = 0; index < patterns.length; index++) {
			String string = patterns[index];
			String value = strings[index];

			if (map.containsKey(string)) {
				if (!map.get(string).equals(value)) {
					return false;
				}
			} else {
				map.put(string, value);
			}
		}

		return map.size() == new HashSet<>(Arrays.asList(strings)).size();
	}
}
