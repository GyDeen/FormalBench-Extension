import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DiscriminantValue {

	public static List<Object> discriminantValue(int num, int n, int c) {
		List<Object> list = new ArrayList<>();
		String source = "";
		int discriminant = (n * n) - (4 * num * c);
		if (discriminant > 0) {
			source = "Two solutions";
			list.add(source);
			list.add(discriminant);
		} else if (discriminant == 0) {
			source = "one solution";
			list.add(source);
			list.add(discriminant);
		} else if (discriminant < 0) {
			source = "no real solution";
			list.add(source);
			list.add(discriminant);
		}
		return list;
	}
}
