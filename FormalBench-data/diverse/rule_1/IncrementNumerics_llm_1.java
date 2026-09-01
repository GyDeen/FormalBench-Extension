import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IncrementNumerics {

	public static List<String> incrementNumerics(List<String> testList, int increment) {
		for (int K = 0; K < testList.size(); K++) {
			String[] temp = testList.get(K).split(" ");
			for (int n = 0; n < temp.length; n++) {
				if (temp[n].matches("\\d+")) {
					int counter = Integer.parseInt(temp[n]);
					counter += increment;
					temp[n] = String.valueOf(counter);
				}
			}
			testList.set(K, String.join(" ", temp));
		}
		return testList;
	}
}
