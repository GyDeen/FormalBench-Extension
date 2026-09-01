import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TestThreeEqual {

	public static int testThreeEqual(int test, int b, int Z) {
		if (test == b && b == Z)
			return 3;
		else if (test == b || b == Z
				|| test == Z)
			return 2;
		else
			return 0;
	}
}
