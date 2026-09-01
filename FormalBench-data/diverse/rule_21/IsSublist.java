
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSublist {

	public static Boolean isSublist(int[] l, int[] s) {
		if (null == l || null == s) {
			return false;
		}

		if (0 == l.length || 0 == s.length || s.length > l.length) {
			return false;
		}

		for (int i = 0; i <= l.length - s.length; i++) {
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
