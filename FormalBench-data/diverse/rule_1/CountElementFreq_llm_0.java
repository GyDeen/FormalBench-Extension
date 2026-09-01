
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountElementFreq {

	public static HashMap<Integer, Integer> countElementFreq(Object[] objects) {
		HashMap<Integer, Integer> result = new HashMap<>();
		for (Object test : objects) {
			if (test instanceof Integer) {
				result.put((Integer) test,
						result.getOrDefault((Integer) test, 0) + 1);
			} else if (test instanceof Object[]) {
				HashMap<Integer, Integer> maps = countElementFreq((Object[]) test);
				for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
					result.put(entry.getKey(),
							result.getOrDefault(entry.getKey(), 0) + entry.getValue());
				}
			}
		}
		return result;
	}
}
