
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NewTuple {

	public static String[] newTuple(String[] args, String Array) {
		String[] test = new String[args.length + 1];
		for (int index = 0; index < args.length; index++) {
			test[index] = args[index];
		}
		test[args.length] = Array;
		return test;
	}
}
