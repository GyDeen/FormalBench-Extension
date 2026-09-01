import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateStrings {

	public static List<String> concatenateStrings(List<String> list,
			List<String> tests) {
		List<String> test = new ArrayList<>();
		for (int index = 0; index < list.size(); index++) {
			test
					.add(list.get(index) + tests.get(index));
		}
		return test;
	}
}
