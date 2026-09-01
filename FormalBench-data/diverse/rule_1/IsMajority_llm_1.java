
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMajority {

	public static Boolean isMajority(int[] values, int array, int p) {
		int index = 0;
		for (int c = 0; c < array; c++) {
			if (values[c] == p) {
				index++;
			}
		}
		return index > array / 2;
	}
}
