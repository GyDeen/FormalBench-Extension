
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FlattenTuple {

	public static String flattenTuple(String[][] tu) {
		StringBuilder builder = new StringBuilder();
		for (String[] test : tu) {
			for (String Array : test) {
				builder.append(Array).append(" ");
			}
		}
		return builder.toString().trim();
	}
}
