import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TestThreeEqual {

	public static int testThreeEqual(int x, int y, int z) {
		boolean b = x == y;
		boolean temp = y == z;
		boolean test = x == y || y == z;
		boolean tmp = x == z;
		if (b && temp)
			return 3;
		else if (test || tmp)
			return 2;
		else
			return 0;
	}
}
