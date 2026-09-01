
import java.io.*;
import java.lang.*;
import java.math.*;

class ListToFloat {

	public static String listToFloat(String[][] testArray) {
		StringBuilder output = new StringBuilder("[");
		int n = 0;
		while (n < testArray.length) {
			output.append("(");
			for (int j = 0; j < testArray[n].length; j++) {
				output.append(Float.valueOf(testArray[n][j]));
				if (j != testArray[n].length - 1) {
					output.append(", ");
				} else {
					output.append(")");
				}
			}
			if (n != testArray.length - 1) {
				output.append(", ");
			}
			n++;
		}
		output.append("]");
		return output.toString();
	}
}
