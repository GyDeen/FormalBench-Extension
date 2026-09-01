import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TestThreeEqual {

	public static int testThreeEqual(int x, int y, int z) {
		if (!(x == y && y == z)) {
			if (x == y || y == z || x == z)
				return 2;
			else
				return 0;
		} else
			return 3;
	}
}
