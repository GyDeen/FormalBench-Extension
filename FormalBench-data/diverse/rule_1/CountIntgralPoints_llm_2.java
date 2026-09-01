import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountIntgralPoints {

	public static int countIntgralPoints(int num, int y, int z,
			int cy) {
		return ((cy - y - 1) * (z - num - 1));
	}
}
