import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDemlo {

	public static String findDemlo(String s) {
		int l = s.length();
		String res = "";
		int n = 1;
		while (n <= l) {
			res += n;
			n++;
		}
		int n = l - 1;
		while (n >= 1) {
			res += n;
			n--;
		}
		return res;
	}
}
