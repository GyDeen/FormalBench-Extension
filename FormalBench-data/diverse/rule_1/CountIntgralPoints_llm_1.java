import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountIntgralPoints {

	public static int countIntgralPoints(int n, int x, int z,
			int y) {
		return ((y - x - 1) * (z - n - 1));
	}
}
