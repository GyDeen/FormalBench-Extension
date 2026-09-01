
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FlattenTuple {

	public static String flattenTuple(String[][] arr) {
		StringBuilder builder = new StringBuilder();
		for (String[] test : arr) {
			for (String string : test) {
				builder.append(string).append(" ");
			}
		}
		return builder.toString().trim();
	}
}
