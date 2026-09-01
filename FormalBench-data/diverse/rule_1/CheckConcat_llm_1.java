import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckConcat {

	public static Boolean checkConcat(String str1, String str2) {
		int ret = str1.length();
		int len = str2.length();
		if (ret % len == 0) {
			int result = ret / len;
			String s = "";
			for (int j = 0; j < result; j++) {
				s += str2;
			}
			return s.equals(str1);
		}
		return false;
	}
}
