
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateTuple {

	public static String concatenateTuple(Object[] objects) {
		StringBuilder builder = new StringBuilder();
		for (int id = 0; id < objects.length; id++) {
			builder.append(objects[id]);
			if (id < objects.length - 1) {
				builder.append("-");
			}
		}
		return builder.toString();
	}
}
