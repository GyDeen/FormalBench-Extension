
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountTuplex {

	public static int countTuplex(int[] tuplex, int value) {
		int count = 0;
		for (int i = 0; i < tuplex.length; i++) {
			if (tuplex[i] == value) {
				count += 1;
			}
		}
		return count;
	}
}
