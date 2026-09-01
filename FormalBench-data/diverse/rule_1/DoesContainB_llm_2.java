import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DoesContainB {

	public static Boolean doesContainB(int B, int n, int d) {
		if (B == n && B == d)
			return true;
		if ((B >= n && B >= d)
				|| (n >= B && n >= d))
			return true;
		return false;
	}
}
