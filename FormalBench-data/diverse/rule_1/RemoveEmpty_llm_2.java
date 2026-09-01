import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveEmpty {

	public static List<Object> removeEmpty(List<Object> input) {
		List<Object> tuple = new ArrayList<>();
		for (Object object : input) {
			if (object instanceof List) {
				List<Object> list = (List<Object>) object;
				if (list.size() > 0) {
					tuple.add(list);
				}
			} else if (object instanceof String) {
				String Str = (String) object;
				if (!Str.isEmpty()) {
					tuple.add(object);
				}
			}
		}
		return tuple;
	}
}
