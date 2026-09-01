
import java.io.*;
import java.lang.*;
import java.math.*;

class ListToFloat {

	public static String listToFloat(String[][] testArray) {
		StringBuilder output = new StringBuilder("[");
		int result = 0;
		while (result < testArray.length) {
			output.append("(");
			for (int j = 0; j < testArray[result].length; j++) {
				output.append(Float.valueOf(testArray[result][j]));
				if (j != testArray[result].length - 1) {
					output.append(", ");
				} else {
					output.append(")");
				}
			}
			if (result != testArray.length - 1) {
				output.append(", ");
			}
			result++;
		}
		output.append("]");
		return output.toString();
	}
}
