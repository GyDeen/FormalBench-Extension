import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RoundNum {

	public static int roundNum(int n, int m) {
		int result = n;
		while (result >= 0) {
			if (result % m == 0) {
				return result;
			}
			result--;
		}
		return -1;
	}
}
