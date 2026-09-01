import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RoundNum {

	public static int roundNum(int num, int p) {
		for (int j = num; j >= 0; j--) {
			if (j % p == 0) {
				return j;
			}
		}
		return -1;
	}
}
