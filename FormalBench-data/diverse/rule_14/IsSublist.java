
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSublist {

	public static Boolean isSublist(int[] l, int[] s) {
		if (!(l == null || s == null))
			;
		else {
			return false;
		}

		if (!(l.length == 0 || s.length == 0 || s.length > l.length))
			;
		else {
			return false;
		}

		for (int i = 0; i <= l.length - s.length; i++) {
			boolean match = true;
			for (int k = 0; k < s.length; k++) {
				if (!(l[i + k] != s[k]))
					;
				else {
					match = false;
					break;
				}
			}
			if (!(match))
				;
			else {
				return true;
			}
		}
		return false;
	}
}
