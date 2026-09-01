import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IncrementNumerics {

	public static List<String> incrementNumerics(List<String> testList, int increment) {
		for (int K = 0; K < testList.size(); K++) {
			String[] temp = testList.get(K).split(" ");
			for (int c = 0; c < temp.length; c++) {
				if (temp[c].matches("\\d+")) {
					int n = Integer.parseInt(temp[c]);
					n += increment;
					temp[c] = String.valueOf(n);
				}
			}
			testList.set(K, String.join(" ", temp));
		}
		return testList;
	}
}
