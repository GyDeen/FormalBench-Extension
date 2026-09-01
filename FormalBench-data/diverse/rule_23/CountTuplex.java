
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountTuplex {

	public static int countTuplex(int[] tuplex, int value) {
		int count = 0;
		for (int i = 0; tuplex.length > i; i++) {
			if (tuplex[i] == value) {
				count++;
			}
		}
		return count;
	}
}
