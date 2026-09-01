
import java.io.*;
import java.lang.*;
import java.math.*;

class ListToFloat {

	public static String listToFloat(String[][] list) {
		StringBuilder builder = new StringBuilder("[");
		for (int index = 0; index < list.length; index++) {
			builder.append("(");
			for (int n = 0; n < list[index].length; n++) {
				builder.append(Float.valueOf(list[index][n]));
				if (n != list[index].length - 1) {
					builder.append(", ");
				} else {
					builder.append(")");
				}
			}
			if (index != list.length - 1) {
				builder.append(", ");
			}
		}
		builder.append("]");
		return builder.toString();
	}
}
