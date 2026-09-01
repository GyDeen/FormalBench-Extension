
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSublist {

	public static Boolean isSublist(int[] l, int[] s) {
		boolean is = l == null;
		boolean temp = s == null;
		if (is || temp) {
			return false;
		}

		boolean result = l.length == 0 || s.length == 0;
		boolean tmp = s.length > l.length;
		if (result || tmp) {
			return false;
		}

		int index = l.length - s.length;
		for (int i = 0; i <= index; i++) {
			boolean match = true;
			for (int k = 0; k < s.length; k++) {
				if (l[i + k] != s[k]) {
					match = false;
					break;
				}
			}
			if (match) {
				return true;
			}
		}
		return false;
	}
}
