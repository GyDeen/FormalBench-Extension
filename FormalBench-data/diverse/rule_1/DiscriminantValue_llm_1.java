import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DiscriminantValue {

	public static List<Object> discriminantValue(int num, int n, int c) {
		List<Object> arr = new ArrayList<>();
		String string = "";
		int discriminant = (n * n) - (4 * num * c);
		if (discriminant > 0) {
			string = "Two solutions";
			arr.add(string);
			arr.add(discriminant);
		} else if (discriminant == 0) {
			string = "one solution";
			arr.add(string);
			arr.add(discriminant);
		} else if (discriminant < 0) {
			string = "no real solution";
			arr.add(string);
			arr.add(discriminant);
		}
		return arr;
	}
}
