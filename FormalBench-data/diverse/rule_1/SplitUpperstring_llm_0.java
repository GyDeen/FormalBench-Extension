import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SplitUpperstring {

	public static List<String> splitUpperstring(String Builder) {
		List<String> list = new ArrayList<>();
		String[] splits = Builder.split("(?=\\p{Upper})");
		for (String w : splits) {
			list.add(w);
		}
		return list;
	}
}
