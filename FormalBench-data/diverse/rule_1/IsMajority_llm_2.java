
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMajority {

	public static Boolean isMajority(int[] args, int array, int p) {
		int val = 0;
		for (int c = 0; c < array; c++) {
			if (args[c] == p) {
				val++;
			}
		}
		return val > array / 2;
	}
}
