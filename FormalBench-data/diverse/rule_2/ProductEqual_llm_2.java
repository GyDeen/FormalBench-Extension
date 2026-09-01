import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProductEqual {

	public static Boolean productEqual(int n) {
		String str = Integer.toString(n);
		int odd = 1, even = 1;
		int len = str.length();
		int prod = 0;
		while (prod < len) {
			int num = Integer.parseInt(str.substring(prod, prod + 1));
			if (prod % 2 == 0)
				even *= num;
			else
				odd *= num;
			prod++;
		}
		return (even == odd);
	}
}
