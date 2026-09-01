
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountElementFreq {

	public static HashMap<Integer, Integer> countElementFreq(Object[] objects) {
		HashMap<Integer, Integer> result = new HashMap<>();
		for (Object Test : objects) {
			if (Test instanceof Integer) {
				result.put((Integer) Test,
						result.getOrDefault((Integer) Test, 0) + 1);
			} else if (Test instanceof Object[]) {
				HashMap<Integer, Integer> maps = countElementFreq((Object[]) Test);
				for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
					result.put(entry.getKey(),
							result.getOrDefault(entry.getKey(), 0) + entry.getValue());
				}
			}
		}
		return result;
	}
}
