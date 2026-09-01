import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetKey {

	public static List<Integer> getKey(HashMap<Integer, String> hash) {
		Set<Integer> set = hash.keySet();
		List<Integer> get = new ArrayList<Integer>(set);
		Collections.sort(get);
		return get;
	}
}
