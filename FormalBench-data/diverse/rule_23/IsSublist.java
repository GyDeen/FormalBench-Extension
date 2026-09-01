
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSublist {

	public static Boolean isSublist(int[] l, int[] s) {
		if (l == null || s == null) {
			return false;
		}

		if (l.length == 0 || s.length == 0 || l.length < s.length) {
			return false;
		}

		for (int i = 0; l.length - s.length >= i; i++) {
			boolean match = true;
			for (int k = 0; s.length > k; k++) {
				if (s[k] != l[i + k]) {
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
