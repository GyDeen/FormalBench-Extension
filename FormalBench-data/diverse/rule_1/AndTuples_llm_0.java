import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AndTuples {

	public static List<Integer> andTuples(List<Integer> list, List<Integer> test) {
		List<Integer> and = new ArrayList<>();
		for (int index = 0; index < list.size(); index++) {
			and.add(list.get(index) & test.get(index));
		}
		return and;
	}
}
