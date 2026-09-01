
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateTuple {

	public static String concatenateTuple(Object[] objects) {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < objects.length; index++) {
			builder.append(objects[index]);
			if (index < objects.length - 1) {
				builder.append("-");
			}
		}
		return builder.toString();
	}
}
