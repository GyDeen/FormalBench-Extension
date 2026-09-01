
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractEven {

	public static Object[] extractEven(Object[] extract) {
		List<Object> test = new ArrayList<>();
		for (Object obj : extract) {
			if (obj instanceof Object[]) {
				Object[] arr = extractEven((Object[]) obj);
				if (arr.length == 1)
					test.add(arr[0]);
				else
					test.add(arr);
			} else if (obj instanceof Integer && ((Integer) obj) % 2 == 0)
				test.add(obj);
		}
		return test.toArray();
	}
}
