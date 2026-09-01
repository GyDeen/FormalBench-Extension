import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AndTuples {

	public static List<Integer> andTuples(List<Integer> list, List<Integer> test) {
		List<Integer> empty = new ArrayList<>();
		for (int index = 0; index < list.size(); index++) {
			empty.add(list.get(index) & test.get(index));
		}
		return empty;
	}
}
