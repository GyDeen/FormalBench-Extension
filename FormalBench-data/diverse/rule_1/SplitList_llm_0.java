
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SplitList {

	public static String[] splitList(String Array) {
		String[] splits = Array.split("(?=\\p{Upper})");

		return splits;
	}
}
