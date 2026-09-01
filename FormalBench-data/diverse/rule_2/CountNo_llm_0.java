import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNo {

	public static int countNo(int a, int n, int l, int r) {
		int count = 0;
		int result = l;
		while (result <= r) {
			if (result % a != 0) {
				count++;
			}
			if (count == n) {
				return result;
			}
			result++;
		}
		return -1;
	}
}
