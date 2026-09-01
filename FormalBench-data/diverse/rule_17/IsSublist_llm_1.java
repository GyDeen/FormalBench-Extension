
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSublist {

	public static Boolean isSublist(int[] l, int[] s) {
		boolean is = l == null;
		boolean result = s == null;
		if (is || result) {
			return false;
		}

		boolean ret = l.length == 0 || s.length == 0;
		boolean tmp = s.length > l.length;
		if (ret || tmp) {
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
