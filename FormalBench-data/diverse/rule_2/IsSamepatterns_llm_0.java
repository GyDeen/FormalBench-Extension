
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSamepatterns {

	public static Boolean isSamepatterns(String[] colors, String[] patterns) {
		Map<String, String> patternToColorMap = new HashMap<>();
		int i = 0;
		while (i < patterns.length) {
			String pattern = patterns[i];
			String color = colors[i];
			if (patternToColorMap.containsKey(pattern)) {
				if (!patternToColorMap.get(pattern).equals(color)) {
					return false;
				}
			} else {
				patternToColorMap.put(pattern, color);
			}
			i++;
		}

		return patternToColorMap.size() == new HashSet<>(Arrays.asList(colors)).size();
	}
}
