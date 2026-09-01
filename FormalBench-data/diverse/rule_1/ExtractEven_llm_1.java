
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractEven {

	public static Object[] extractEven(Object[] extract) {
		List<Object> result = new ArrayList<>();
		for (Object test : extract) {
			if (test instanceof Object[]) {
				Object[] arr = extractEven((Object[]) test);
				if (arr.length == 1)
					result.add(arr[0]);
				else
					result.add(arr);
			} else if (test instanceof Integer && ((Integer) test) % 2 == 0)
				result.add(test);
		}
		return result.toArray();
	}
}
