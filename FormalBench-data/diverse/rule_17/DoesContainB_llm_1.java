import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DoesContainB {

	public static Boolean doesContainB(int a, int b, int c) {
		boolean is = a == b;
		boolean temp = a == c;
		if (is && temp)
			return true;
		boolean result = a >= b && a >= c;
		boolean tmp = b >= a && b >= c;
		if ((result) || (tmp))
			return true;
		return false;
	}
}
