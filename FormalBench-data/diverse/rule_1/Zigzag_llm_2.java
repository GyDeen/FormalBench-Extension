import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Zigzag {

	public static int zigzag(int seed, int m) {
		if (seed == 0 && m == 0) {
			return 1;
		}
		if (m == 0) {
			return 0;
		}
		return zigzag(seed, m - 1) + zigzag(seed - 1, seed - m);
	}
}
