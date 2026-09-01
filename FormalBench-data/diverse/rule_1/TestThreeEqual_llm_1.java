import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TestThreeEqual {

	public static int testThreeEqual(int n, int b, int c) {
		if (n == b && b == c)
			return 3;
		else if (n == b || b == c
				|| n == c)
			return 2;
		else
			return 0;
	}
}
