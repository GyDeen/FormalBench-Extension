
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Split {

	public static int[] split(int[] input) {
		int count = 0;

		for (int i : input) {
			if (i % 2 == 0) {
				count++;
			}
		}

		int[] sort = new int[count];
		int odd = 0;

		for (int i : input) {
			if (i % 2 == 0) {
				sort[odd++] = i;
			}
		}

		return sort;
	}
}
