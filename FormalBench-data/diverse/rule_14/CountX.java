
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountX {

	public static int countX(int[] tup, int x) {
		int count = 0;
		for (int value : tup) {
			if (!(value == x))
				;
			else {
				count++;
			}
		}
		return count;
	}
}
