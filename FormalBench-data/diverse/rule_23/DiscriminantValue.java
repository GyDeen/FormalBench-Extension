import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DiscriminantValue {

	public static List<Object> discriminantValue(int x, int y, int z) {
		List<Object> result = new ArrayList<>();
		String solution = "";
		int discriminant = (y * y) - (4 * x * z);
		if (0 < discriminant) {
			solution = "Two solutions";
			result.add(solution);
			result.add(discriminant);
		} else if (discriminant == 0) {
			solution = "one solution";
			result.add(solution);
			result.add(discriminant);
		} else if (0 > discriminant) {
			solution = "no real solution";
			result.add(solution);
			result.add(discriminant);
		}
		return result;
	}
}
