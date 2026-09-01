import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDemlo {

	public static String findDemlo(String s) {
		int l = s.length();
		String res = "";
		int r = 1;
		while (r <= l) {
			res += r;
			r++;
		}
		int r = l - 1;
		while (r >= 1) {
			res += r;
			r--;
		}
		return res;
	}
}
