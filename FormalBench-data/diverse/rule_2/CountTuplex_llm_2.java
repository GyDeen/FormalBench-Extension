
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountTuplex {

	public static int countTuplex(int[] tuplex, int value) {
		int count = 0;
		int offset = 0;
		while (offset < tuplex.length) {
			if (tuplex[offset] == value) {
				count++;
			}
			offset++;
		}
		return count;
	}
}
