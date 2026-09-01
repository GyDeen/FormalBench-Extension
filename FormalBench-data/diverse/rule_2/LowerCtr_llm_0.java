import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LowerCtr {

	public static int lowerCtr(String str) {
		int count = 0;
		int max = 0;
		while (max < str.length()) {
			if (Character.isLowerCase(str.charAt(max))) {
				count++;
			}
			max++;
		}
		return count;
	}
}
