import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TestThreeEqual {

	public static int testThreeEqual(int test, int b, int c) {
		if (test == b && b == c)
			return 3;
		else if (test == b || b == c
				|| test == c)
			return 2;
		else
			return 0;
	}
}
