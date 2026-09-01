import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckConcat {

	public static Boolean checkConcat(String str1, String str2) {
		int result = str1.length();
		int len = str2.length();
		if (result % len == 0) {
			int ret = result / len;
			String s = "";
			for (int j = 0; j < ret; j++) {
				s += str2;
			}
			return s.equals(str1);
		}
		return false;
	}
}
