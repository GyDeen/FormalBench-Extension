
import java.io.*;
import java.lang.*;

class TupString {

	public static String tupString(String[] args) {
		StringBuilder builder = new StringBuilder();
		for (String a : args) {
			builder.append(a);
		}
		return builder.toString();
	}
}
