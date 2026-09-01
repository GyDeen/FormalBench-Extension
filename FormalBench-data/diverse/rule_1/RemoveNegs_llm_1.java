
import java.io.*;
import java.lang.*;
import java.util.*;

class RemoveNegs {

	public static int[] removeNegs(int[] array) {
		int arr = 0;
		for (int c : array) {
			if (c >= 0) {
				arr++;
			}
		}

		int[] remove = new int[arr];
		int index = 0;
		for (int c : array) {
			if (c >= 0) {
				remove[index++] = c;
			}
		}

		return remove;
	}
}
