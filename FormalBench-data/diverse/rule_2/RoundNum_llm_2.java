import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RoundNum {

	public static int roundNum(int n, int m) {
		int i = n;
		while (i >= 0) {
			if (i % m == 0) {
				return i;
			}
			i--;
		}
		return -1;
	}
}
