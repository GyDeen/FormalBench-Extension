import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddDictToTuple {

	public static List<Object> addDictToTuple(List<Integer> lists,
			HashMap<String, Integer> map) {
		List<Object> result = new ArrayList<>();
		for (Integer test : lists) {
			result.add(test);
		}
		result.add(map);
		return result;
	}
}
