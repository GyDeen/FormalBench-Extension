import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TestThreeEqual {

	public static int testThreeEqual(int x, int y, int z) {
		if (y == x && z == y)
			return 3;
		else if (y == x || z == y || z == x)
			return 2;
		else
			return 0;
	}
}
