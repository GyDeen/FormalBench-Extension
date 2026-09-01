import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IncrementNumerics {

	public static List<String> incrementNumerics(List<String> testList, int increment) {
		for (int n = 0; n < testList.size(); n++) {
			String[] temp = testList.get(n).split(" ");
			for (int c = 0; c < temp.length; c++) {
				if (temp[c].matches("\\d+")) {
					int counter = Integer.parseInt(temp[c]);
					counter += increment;
					temp[c] = String.valueOf(counter);
				}
			}
			testList.set(n, String.join(" ", temp));
		}
		return testList;
	}
}
