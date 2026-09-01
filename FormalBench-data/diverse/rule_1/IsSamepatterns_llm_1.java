
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSamepatterns {

	public static Boolean isSamepatterns(String[] strings, String[] patterns) {
		Map<String, String> map = new HashMap<>();
		for (int index = 0; index < patterns.length; index++) {
			String Pattern = patterns[index];
			String value = strings[index];

			if (map.containsKey(Pattern)) {
				if (!map.get(Pattern).equals(value)) {
					return false;
				}
			} else {
				map.put(Pattern, value);
			}
		}

		return map.size() == new HashSet<>(Arrays.asList(strings)).size();
	}
}
