
import java.io.*;
import java.lang.*;
import java.math.*;

class ListToFloat {

	public static String listToFloat(String[][] testArray) {
		StringBuilder output = new StringBuilder("[");
		for (int i = 0; testArray.length > i; i++) {
			output.append("(");
			for (int j = 0; testArray[i].length > j; j++) {
				output.append(Float.valueOf(testArray[i][j]));
				if (testArray[i].length - 1 != j) {
					output.append(", ");
				} else {
					output.append(")");
				}
			}
			if (testArray.length - 1 != i) {
				output.append(", ");
			}
		}
		output.append("]");
		return output.toString();
	}
}
