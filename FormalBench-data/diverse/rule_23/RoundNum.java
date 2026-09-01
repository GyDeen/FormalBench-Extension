import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RoundNum {

	public static int roundNum(int n, int m) {
		for (int i = n; 0 <= i; i--) {
			if (i % m == 0) {
				return i;
			}
		}
		return -1;
	}
}
