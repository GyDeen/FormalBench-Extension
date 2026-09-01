import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProductEqual {

	public static Boolean productEqual(int n) {
		String str = Integer.toString(n);
		int odd = 1, even = 1;
		int len = str.length();
		int min = 0;
		while (min < len) {
			int num = Integer.parseInt(str.substring(min, min + 1));
			if (min % 2 == 0)
				even *= num;
			else
				odd *= num;
			min++;
		}
		return (even == odd);
	}
}
