
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountElementFreq {

	public static HashMap<Integer, Integer> countElementFreq(Object[] testTuple) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (Object item : testTuple) {
			if (item instanceof Integer) {
				map.put((Integer) item, map.getOrDefault((Integer) item, 0) + 1);
			} else {
				if (item instanceof Object[]) {
					HashMap<Integer, Integer> temp = countElementFreq((Object[]) item);
					for (Map.Entry<Integer, Integer> e : temp.entrySet()) {
						map.put(e.getKey(), map.getOrDefault(e.getKey(), 0) + e.getValue());
					}
				}
			}
		}
		return map;
	}
}
