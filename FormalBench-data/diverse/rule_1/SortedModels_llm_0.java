
import java.io.*;
import java.util.*;
import java.math.*;

class SortedModels {

	public static HashMap<String, Object>[] sortedModels(HashMap<String, Object>[] map) {
		Arrays.sort(map, (Sort, m) -> {
			return Integer.compare((int) m.get("model"), (int) Sort.get("model"));
		});
		return map;
	}
}
