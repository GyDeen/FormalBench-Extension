import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveEmpty {

	public static List<Object> removeEmpty(List<Object> input) {
		List<Object> list = new ArrayList<>();
		for (Object object : input) {
			if (object instanceof List) {
				List<Object> tuple = (List<Object>) object;
				if (tuple.size() > 0) {
					list.add(tuple);
				}
			} else if (object instanceof String) {
				String Str = (String) object;
				if (!Str.isEmpty()) {
					list.add(object);
				}
			}
		}
		return list;
	}
}
