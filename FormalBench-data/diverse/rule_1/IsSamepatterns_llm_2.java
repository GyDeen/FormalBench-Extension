
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSamepatterns {

	public static Boolean isSamepatterns(String[] strings, String[] patterns) {
		Map<String, String> maps = new HashMap<>();
		for (int index = 0; index < patterns.length; index++) {
			String string = patterns[index];
			String value = strings[index];

			if (maps.containsKey(string)) {
				if (!maps.get(string).equals(value)) {
					return false;
				}
			} else {
				maps.put(string, value);
			}
		}

		return maps.size() == new HashSet<>(Arrays.asList(strings)).size();
	}
}
