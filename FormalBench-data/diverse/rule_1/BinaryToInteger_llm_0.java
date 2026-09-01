
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinaryToInteger {

	public static String binaryToInteger(int[] bytes) {
		int test = 0;
		for (int index = 0; index < bytes.length; index++) {
			test = (test << 1) | bytes[index];
		}
		return Integer.toString(test);
	}
}
