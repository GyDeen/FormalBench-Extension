import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProductEqual {

	public static Boolean productEqual(int n) {
		String str = Integer.toString(n);
		int odd = 1, even = 1, len = str.length();
		for (int i = 0; i < len; i++) {
			int num = Integer.parseInt(str.substring(i, i + 1));
			if (i % 2 == 0)
				even *= num;
			else
				odd *= num;
		}
		return (even == odd);
	}
}
