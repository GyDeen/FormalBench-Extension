
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinaryToInteger {

	public static String binaryToInteger(int[] bytes) {
		int result = 0;
		for (int index = 0; index < bytes.length; index++) {
			result = (result << 1) | bytes[index];
		}
		return Integer.toString(result);
	}
}
