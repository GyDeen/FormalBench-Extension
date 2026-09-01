import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetKey {

	public static List<Integer> getKey(HashMap<Integer, String> map) {
		Set<Integer> set = map.keySet();
		List<Integer> get = new ArrayList<Integer>(set);
		Collections.sort(get);
		return get;
	}
}
