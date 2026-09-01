import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RoundNum {

	public static int roundNum(int seed, int p) {
		for (int c = seed; c >= 0; c--) {
			if (c % p == 0) {
				return c;
			}
		}
		return -1;
	}
}
