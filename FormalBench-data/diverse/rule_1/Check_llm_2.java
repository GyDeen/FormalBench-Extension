import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Check {

	public static Boolean check(int code) {
		int i = code;
		int result = 0;
		while (i > 0) {
			int test = i % 10;
			result += test * test;
			i = i / 10;
		}
		return code == 0 || result % 2 == 0;
	}
}
