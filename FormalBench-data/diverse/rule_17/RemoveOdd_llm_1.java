import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveOdd {

	public static String removeOdd(String str1) {
		int n = str1.length();
		String str2 = "";
		for (int i = 0; i < n; i++) {
			int tmp = i % 2;
			if (tmp != 0) {
				str2 = str2 + str1.charAt(i);
			}
		}
		return str2;
	}
}
