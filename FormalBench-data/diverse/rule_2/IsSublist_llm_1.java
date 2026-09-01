
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSublist {

	public static Boolean isSublist(int[] l, int[] s) {
		if (l == null || s == null) {
			return false;
		}

		if (l.length == 0 || s.length == 0 || s.length > l.length) {
			return false;
		}

		int i = 0;
		while (i <= l.length - s.length) {
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
			i++;
		}
		return false;
	}
}
