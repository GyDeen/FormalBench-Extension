import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveEmpty {

	public static List<Object> removeEmpty(List<Object> tuple1) {
		List<Object> tuple2 = new ArrayList<>();
		for (Object t : tuple1) {
			if (!(t instanceof List)) {
				if (t instanceof String) {
					String str = (String) t;
					if (!str.isEmpty()) {
						tuple2.add(t);
					}
				}
			} else {
				List<Object> tuple3 = (List<Object>) t;
				if (tuple3.size() > 0) {
					tuple2.add(tuple3);
				}
			}
		}
		return tuple2;
	}
}
