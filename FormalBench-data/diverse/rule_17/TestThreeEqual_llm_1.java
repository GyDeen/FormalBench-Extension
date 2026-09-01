import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TestThreeEqual {

	public static int testThreeEqual(int x, int y, int z) {
		boolean result = x == y;
		boolean temp = y == z;
		boolean ret = x == y || y == z;
		boolean tmp = x == z;
		if (result && temp)
			return 3;
		else if (ret || tmp)
			return 2;
		else
			return 0;
	}
}
