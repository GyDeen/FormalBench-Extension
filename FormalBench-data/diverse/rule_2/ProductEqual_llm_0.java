import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProductEqual {

	public static Boolean productEqual(int n) {
		String str = Integer.toString(n);
		int odd = 1, even = 1;
		int len = str.length();
		int max = 0;
		while (max < len) {
			int num = Integer.parseInt(str.substring(max, max + 1));
			if (max % 2 == 0)
				even *= num;
			else
				odd *= num;
			max++;
		}
		return (even == odd);
	}
}
