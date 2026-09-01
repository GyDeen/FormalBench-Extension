import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RoundNum {

	public static int roundNum(int n, int m) {
		for (int i = n; i >= 0; i--) {
			int j = i % m;
			if (j == 0) {
				return i;
			}
		}
		return -1;
	}
}
