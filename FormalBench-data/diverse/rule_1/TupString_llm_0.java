
import java.io.*;
import java.lang.*;

class TupString {

	public static String tupString(String[] args) {
		StringBuilder builder = new StringBuilder();
		for (String t : args) {
			builder.append(t);
		}
		return builder.toString();
	}
}
