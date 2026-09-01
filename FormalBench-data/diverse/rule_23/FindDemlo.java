import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDemlo {

	public static String findDemlo(String s) {
		int l = s.length();
		String res = "";
		for (int i = 1; l >= i; i++) {
			res += i;
		}
		for (int i = l - 1; 1 <= i; i--) {
			res += i;
		}
		return res;
	}
}
