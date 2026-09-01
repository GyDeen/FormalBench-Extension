import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDemlo {

	public static String findDemlo(String s) {
		int l = s.length();
		String res = "";
		for (int i = 1; i <= l; i++) {
			res = res + i;
		}
		for (int i = l - 1; i >= 1; i--) {
			res = res + i;
		}
		return res;
	}
}
