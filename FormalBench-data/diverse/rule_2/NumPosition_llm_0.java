import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumPosition {

	public static int numPosition(String text) {
		int ret = 0;
		while (ret < text.length()) {
			if (Character.isDigit(text.charAt(ret))) {
				return ret;
			}
			ret++;
		}
		return -1;
	}
}
