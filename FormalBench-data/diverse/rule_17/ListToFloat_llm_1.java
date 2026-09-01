
import java.io.*;
import java.lang.*;
import java.math.*;

class ListToFloat {

	public static String listToFloat(String[][] testArray) {
		StringBuilder output = new StringBuilder("[");
		for (int i = 0; i < testArray.length; i++) {
			output.append("(");
			for (int j = 0; j < testArray[i].length; j++) {
				output.append(Float.valueOf(testArray[i][j]));
				int end = testArray[i].length - 1;
				if (j != end) {
					output.append(", ");
				} else {
					output.append(")");
				}
			}
			int index = testArray.length - 1;
			if (i != index) {
				output.append(", ");
			}
		}
		output.append("]");
		return output.toString();
	}
}
