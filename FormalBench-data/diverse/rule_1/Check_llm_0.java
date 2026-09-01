import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Check {

	public static Boolean check(int code) {
		int i = code;
		int test = 0;
		while (i > 0) {
			int tmp = i % 10;
			test += tmp * tmp;
			i = i / 10;
		}
		return code == 0 || test % 2 == 0;
	}
}
