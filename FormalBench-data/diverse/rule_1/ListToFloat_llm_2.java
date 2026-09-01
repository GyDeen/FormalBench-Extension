
import java.io.*;
import java.lang.*;
import java.math.*;

class ListToFloat {

	public static String listToFloat(String[][] list) {
		StringBuilder builder = new StringBuilder("[");
		for (int count = 0; count < list.length; count++) {
			builder.append("(");
			for (int n = 0; n < list[count].length; n++) {
				builder.append(Float.valueOf(list[count][n]));
				if (n != list[count].length - 1) {
					builder.append(", ");
				} else {
					builder.append(")");
				}
			}
			if (count != list.length - 1) {
				builder.append(", ");
			}
		}
		builder.append("]");
		return builder.toString();
	}
}
