import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemovezeroIp {

	public static String removezeroIp(String ip) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < ip.length()) {
			char ch = ip.charAt(i);
			if (ch != '0')
				sb.append(ch);
			i++;
		}
		return sb.toString();
	}
}
