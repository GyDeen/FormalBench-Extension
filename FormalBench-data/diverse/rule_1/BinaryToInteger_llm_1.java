
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinaryToInteger {

	public static String binaryToInteger(int[] args) {
		int test = 0;
		for (int index = 0; index < args.length; index++) {
			test = (test << 1) | args[index];
		}
		return Integer.toString(test);
	}
}
