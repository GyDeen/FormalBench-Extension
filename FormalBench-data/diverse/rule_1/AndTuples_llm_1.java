import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AndTuples {

	public static List<Integer> andTuples(List<Integer> list, List<Integer> tests) {
		List<Integer> test = new ArrayList<>();
		for (int index = 0; index < list.size(); index++) {
			test.add(list.get(index) & tests.get(index));
		}
		return test;
	}
}
