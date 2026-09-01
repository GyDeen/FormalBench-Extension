
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NewTuple {

	public static String[] newTuple(String[] string, String Array) {
		String[] test = new String[string.length + 1];
		for (int index = 0; index < string.length; index++) {
			test[index] = string[index];
		}
		test[string.length] = Array;
		return test;
	}
}
