
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountTuplex {

	public static int countTuplex(int[] tuplex, int value) {
		int count = 0;
		int result = 0;
		while (result < tuplex.length) {
			if (tuplex[result] == value) {
				count++;
			}
			result++;
		}
		return count;
	}
}
