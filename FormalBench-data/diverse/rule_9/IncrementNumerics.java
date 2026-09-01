import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IncrementNumerics {

	public static List<String> incrementNumerics(List<String> testList, int k) {
		for (int i = 0; i < testList.size(); i++) {
			String[] split = testList.get(i).split(" ");
			for (int j = 0; j < split.length; j++) {
				if (split[j].matches("\\d+")) {
					int num = Integer.parseInt(split[j]);
					num = num + k;
					split[j] = String.valueOf(num);
				}
			}
			testList.set(i, String.join(" ", split));
		}
		return testList;
	}
}
