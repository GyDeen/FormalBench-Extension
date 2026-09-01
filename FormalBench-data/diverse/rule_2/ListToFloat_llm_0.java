
import java.io.*;
import java.lang.*;
import java.math.*;

class ListToFloat {

	public static String listToFloat(String[][] testArray) {
		StringBuilder output = new StringBuilder("[");
		int count = 0;
		while (count < testArray.length) {
			output.append("(");
			for (int j = 0; j < testArray[count].length; j++) {
				output.append(Float.valueOf(testArray[count][j]));
				if (j != testArray[count].length - 1) {
					output.append(", ");
				} else {
					output.append(")");
				}
			}
			if (count != testArray.length - 1) {
				output.append(", ");
			}
			count++;
		}
		output.append("]");
		return output.toString();
	}
}
