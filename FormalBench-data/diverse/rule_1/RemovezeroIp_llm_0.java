import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemovezeroIp {

	public static String removezeroIp(String string) {
		StringBuilder builder = new StringBuilder();
		for (int n = 0; n < string.length(); n++) {
			char c = string.charAt(n);
			if (c != '0')
				builder.append(c);
		}
		return builder.toString();
	}
}
