
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountTuplex {

	public static int countTuplex(int[] tuplex, int value) {
		int count = 0;
		int total = 0;
		while (total < tuplex.length) {
			if (tuplex[total] == value) {
				count++;
			}
			total++;
		}
		return count;
	}
}
