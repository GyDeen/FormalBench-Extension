import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LowerCtr {

	public static int lowerCtr(String str) {
		int count = 0;
		int result = 0;
		while (result < str.length()) {
			if (Character.isLowerCase(str.charAt(result))) {
				count++;
			}
			result++;
		}
		return count;
	}
}
