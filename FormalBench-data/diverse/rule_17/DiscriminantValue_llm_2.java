import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DiscriminantValue {

	public static List<Object> discriminantValue(int x, int y, int z) {
		List<Object> result = new ArrayList<>();
		String solution = "";
		int error = y * y;
		int tmp = 4 * x * z;
		int discriminant = (error) - (tmp);
		if (discriminant > 0) {
			solution = "Two solutions";
			result.add(solution);
			result.add(discriminant);
		} else if (discriminant == 0) {
			solution = "one solution";
			result.add(solution);
			result.add(discriminant);
		} else if (discriminant < 0) {
			solution = "no real solution";
			result.add(solution);
			result.add(discriminant);
		}
		return result;
	}
}
