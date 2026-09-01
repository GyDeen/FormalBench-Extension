import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateStrings {

	public static List<String> concatenateStrings(List<String> input,
			List<String> test) {
		List<String> list = new ArrayList<>();
		for (int index = 0; index < input.size(); index++) {
			list
					.add(input.get(index) + test.get(index));
		}
		return list;
	}
}
