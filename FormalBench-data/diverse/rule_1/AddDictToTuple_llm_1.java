import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddDictToTuple {

	public static List<Object> addDictToTuple(List<Integer> list,
			HashMap<String, Integer> map) {
		List<Object> test = new ArrayList<>();
		for (Integer Test : list) {
			test.add(Test);
		}
		test.add(map);
		return test;
	}
}
