import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemovezeroIp {

	public static String removezeroIp(String ip) {
		StringBuilder sb = new StringBuilder();
		int n = 0;
		while (n < ip.length()) {
			char ch = ip.charAt(n);
			if (ch != '0')
				sb.append(ch);
			n++;
		}
		return sb.toString();
	}
}
