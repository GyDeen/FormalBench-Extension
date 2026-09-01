
import java.io.*;
import java.lang.*;
import java.math.*;

class ListToFloat {

	public static String listToFloat(String[][] array) {
		StringBuilder builder = new StringBuilder("[");
		for (int index = 0; index < array.length; index++) {
			builder.append("(");
			for (int n = 0; n < array[index].length; n++) {
				builder.append(Float.valueOf(array[index][n]));
				if (n != array[index].length - 1) {
					builder.append(", ");
				} else {
					builder.append(")");
				}
			}
			if (index != array.length - 1) {
				builder.append(", ");
			}
		}
		builder.append("]");
		return builder.toString();
	}
}
