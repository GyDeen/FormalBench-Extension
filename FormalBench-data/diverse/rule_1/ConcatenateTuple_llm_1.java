
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateTuple {

	public static String concatenateTuple(Object[] args) {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < args.length; index++) {
			builder.append(args[index]);
			if (index < args.length - 1) {
				builder.append("-");
			}
		}
		return builder.toString();
	}
}
