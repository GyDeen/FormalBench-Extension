
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSamepatterns {

	public static Boolean isSamepatterns(String[] colors, String[] patterns) {
		Map<String, String> patternToColorMap = new HashMap<>();
		int index = 0;
		while (index < patterns.length) {
			String pattern = patterns[index];
			String color = colors[index];
			if (patternToColorMap.containsKey(pattern)) {
				if (!patternToColorMap.get(pattern).equals(color)) {
					return false;
				}
			} else {
				patternToColorMap.put(pattern, color);
			}
			index++;
		}

		return patternToColorMap.size() == new HashSet<>(Arrays.asList(colors)).size();
	}
}
