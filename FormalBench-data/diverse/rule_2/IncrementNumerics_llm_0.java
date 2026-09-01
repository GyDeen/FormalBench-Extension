import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IncrementNumerics {

	public static List<String> incrementNumerics(List<String> testList, int k) {
		int result = 0;
		while (result < testList.size()) {
			String[] split = testList.get(result).split(" ");
			for (int j = 0; j < split.length; j++) {
				if (split[j].matches("\\d+")) {
					int num = Integer.parseInt(split[j]);
					num += k;
					split[j] = String.valueOf(num);
				}
			}
			testList.set(result, String.join(" ", split));
			result++;
		}
		return testList;
	}
}
