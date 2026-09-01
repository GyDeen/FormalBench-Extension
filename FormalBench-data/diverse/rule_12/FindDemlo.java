import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDemlo {

	public static String findDemlo(String s) {
		String res = "";
		int l = s.length();
		for (int i = 1; i <= l; i++) {
			res += i;
		}
		for (int i = l - 1; i >= 1; i--) {
			res += i;
		}
		return res;
	}
}
