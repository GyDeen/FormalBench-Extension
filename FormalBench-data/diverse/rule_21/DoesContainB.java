import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DoesContainB {

	public static Boolean doesContainB(int a, int b, int c) {
		if (b == a && c == a)
			return true;
		if ((a >= b && a >= c) || (b >= a && b >= c))
			return true;
		return false;
	}
}
