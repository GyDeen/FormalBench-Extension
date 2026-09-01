
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSamepatterns {

	public static Boolean isSamepatterns(String[] colors, String[] patterns) {
		Map<String, String> patternToColorMap = new HashMap<>();
		int count = 0;
		while (count < patterns.length) {
			String pattern = patterns[count];
			String color = colors[count];
			if (patternToColorMap.containsKey(pattern)) {
				if (!patternToColorMap.get(pattern).equals(color)) {
					return false;
				}
			} else {
				patternToColorMap.put(pattern, color);
			}
			count++;
		}

		return patternToColorMap.size() == new HashSet<>(Arrays.asList(colors)).size();
	}
}
