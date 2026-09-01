import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckConcat {

	public static Boolean checkConcat(String str1, String str2) {
		int length1 = str1.length();
		int length2 = str2.length();
		if (length1 % length2 == 0) {
			int n = length1 / length2;
			String str = "";
			int len = 0;
			while (len < n) {
				str += str2;
				len++;
			}
			return str.equals(str1);
		}
		return false;
	}
}
