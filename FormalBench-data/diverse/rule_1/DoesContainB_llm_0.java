import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DoesContainB {

	public static Boolean doesContainB(int B, int n, int C) {
		if (B == n && B == C)
			return true;
		if ((B >= n && B >= C)
				|| (n >= B && n >= C))
			return true;
		return false;
	}
}
