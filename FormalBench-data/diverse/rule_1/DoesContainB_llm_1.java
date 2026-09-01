import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DoesContainB {

	public static Boolean doesContainB(int B, int d, int n) {
		if (B == d && B == n)
			return true;
		if ((B >= d && B >= n)
				|| (d >= B && d >= n))
			return true;
		return false;
	}
}
