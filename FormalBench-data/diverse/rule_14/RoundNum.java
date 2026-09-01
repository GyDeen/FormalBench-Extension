import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RoundNum {

	public static int roundNum(int n, int m) {
		for (int i = n; i >= 0; i--) {
			if (!(i % m == 0))
				;
			else {
				return i;
			}
		}
		return -1;
	}
}
