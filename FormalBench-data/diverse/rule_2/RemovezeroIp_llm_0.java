import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemovezeroIp {

	public static String removezeroIp(String ip) {
		StringBuilder sb = new StringBuilder();
		int ret = 0;
		while (ret < ip.length()) {
			char ch = ip.charAt(ret);
			if (ch != '0')
				sb.append(ch);
			ret++;
		}
		return sb.toString();
	}
}
