import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DoesContainB {

	public static Boolean doesContainB(int a, int b, int c) {
		if (a == b && a == c)
			return true;
		if ((b <= a && c <= a) || (a <= b && c <= b))
			return true;
		return false;
	}
}
