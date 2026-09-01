import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveEmpty {

	public static List<Object> removeEmpty(List<Object> list) {
		List<Object> tu = new ArrayList<>();
		for (Object object : list) {
			if (object instanceof List) {
				List<Object> tuple = (List<Object>) object;
				if (tuple.size() > 0) {
					tu.add(tuple);
				}
			} else if (object instanceof String) {
				String Str = (String) object;
				if (!Str.isEmpty()) {
					tu.add(object);
				}
			}
		}
		return tu;
	}
}
