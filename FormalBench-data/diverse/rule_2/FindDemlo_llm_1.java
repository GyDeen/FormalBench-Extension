import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDemlo {

	public static String findDemlo(String s) {
		int l = s.length();
		String res = "";
		int i = 1;
		while (i <= l) {
			res += i;
			i++;
		}
		int i = l - 1;
		while (i >= 1) {
			res += i;
			i--;
		}
		return res;
	}
}
