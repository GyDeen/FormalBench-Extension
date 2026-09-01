import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddString {

	public static List<String> addString(List<Object> args, String str) {
		List<String> ret = new ArrayList<>();
		for (int index = 0; index < args.size(); index++) {
			ret
					.add(str.replace("{0}", String.valueOf(args.get(index))));
		}
		return ret;
	}
}
