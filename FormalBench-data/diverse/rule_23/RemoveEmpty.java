import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveEmpty {

	public static List<Object> removeEmpty(List<Object> tuple1) {
		List<Object> tuple2 = new ArrayList<>();
		for (Object t : tuple1) {
			if (t instanceof List) {
				List<Object> tuple3 = (List<Object>) t;
				if (0 < tuple3.size()) {
					tuple2.add(tuple3);
				}
			} else if (t instanceof String) {
				String str = (String) t;
				if (!str.isEmpty()) {
					tuple2.add(t);
				}
			}
		}
		return tuple2;
	}
}
