
import java.io.*;
import java.util.*;
import java.math.*;

class SortedModels {

	public static HashMap<String, Object>[] sortedModels(HashMap<String, Object>[] map) {
		Arrays.sort(map, (Sort, M) -> {
			return Integer.compare((int) M.get("model"), (int) Sort.get("model"));
		});
		return map;
	}
}
