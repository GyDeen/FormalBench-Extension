
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractEven {

	public static Object[] extractEven(Object[] extract) {
		List<Object> test = new ArrayList<>();
		for (Object object : extract) {
			if (object instanceof Object[]) {
				Object[] arr = extractEven((Object[]) object);
				if (arr.length == 1)
					test.add(arr[0]);
				else
					test.add(arr);
			} else if (object instanceof Integer && ((Integer) object) % 2 == 0)
				test.add(object);
		}
		return test.toArray();
	}
}
