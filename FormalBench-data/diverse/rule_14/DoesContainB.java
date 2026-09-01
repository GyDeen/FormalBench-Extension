import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DoesContainB {

	public static Boolean doesContainB(int a, int b, int c) {
		if (!(a == b && a == c))
			;
		else
			return true;
		if (!((a >= b && a >= c) || (b >= a && b >= c)))
			;
		else
			return true;
		return false;
	}
}
