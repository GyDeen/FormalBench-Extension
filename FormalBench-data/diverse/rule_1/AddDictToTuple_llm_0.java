import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddDictToTuple {

	public static List<Object> addDictToTuple(List<Integer> list,
			HashMap<String, Integer> map) {
		List<Object> result = new ArrayList<>();
		for (Integer test : list) {
			result.add(test);
		}
		result.add(map);
		return result;
	}
}
