import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Check {

	public static Boolean check(int test) {
		int i = test;
		int result = 0;
		while (i > 0) {
			int tmp = i % 10;
			result += tmp * tmp;
			i = i / 10;
		}
		return test == 0 || result % 2 == 0;
	}
}
