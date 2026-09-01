import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DoesContainB {

	public static Boolean doesContainB(int a, int b, int c) {
		boolean is = a == b;
		boolean result = a == c;
		if (is && result)
			return true;
		boolean ret = a >= b && a >= c;
		boolean tmp = b >= a && b >= c;
		if ((ret) || (tmp))
			return true;
		return false;
	}
}
