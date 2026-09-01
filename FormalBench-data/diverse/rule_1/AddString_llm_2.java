import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddString {

	public static List<String> addString(List<Object> objects, String str) {
		List<String> ret = new ArrayList<>();
		for (int index = 0; index < objects.size(); index++) {
			ret
					.add(str.replace("{0}", String.valueOf(objects.get(index))));
		}
		return ret;
	}
}
