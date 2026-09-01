import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddString {

	public static List<String> addString(List<Object> args, String str) {
		List<String> ret = new ArrayList<>();
		for (int id = 0; id < args.size(); id++) {
			ret
					.add(str.replace("{0}", String.valueOf(args.get(id))));
		}
		return ret;
	}
}
