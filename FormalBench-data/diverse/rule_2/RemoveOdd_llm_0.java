import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveOdd {

	public static String removeOdd(String str1) {
		int n = str1.length();
		String str2 = "";
		int i = 0;
		while (i < n) {
			if (i % 2 != 0) {
				str2 = str2 + str1.charAt(i);
			}
			i++;
		}
		return str2;
	}
}
