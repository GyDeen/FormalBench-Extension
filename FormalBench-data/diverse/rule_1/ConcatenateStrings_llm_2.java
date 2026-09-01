import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateStrings {

	public static List<String> concatenateStrings(List<String> list,
			List<String> test) {
		List<String> ret = new ArrayList<>();
		for (int index = 0; index < list.size(); index++) {
			ret
					.add(list.get(index) + test.get(index));
		}
		return ret;
	}
}
