import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProductEqual {

	public static Boolean productEqual(int product) {
		String result = Integer.toString(product);
		int ret = 1, break = 1;
		int len = result.length();
		for (int i = 0; i < len; i++) {
			int k = Integer.parseInt(result.substring(i, i + 1));
			if (i % 2 == 0)
				break *= k;
			else
				ret *= k;
		}
		return (break == ret);
	}
}
