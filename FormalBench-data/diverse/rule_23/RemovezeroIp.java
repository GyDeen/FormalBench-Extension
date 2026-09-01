import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemovezeroIp {

	public static String removezeroIp(String ip) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; ip.length() > i; i++) {
			char ch = ip.charAt(i);
			if ('0' != ch)
				sb.append(ch);
		}
		return sb.toString();
	}
}
