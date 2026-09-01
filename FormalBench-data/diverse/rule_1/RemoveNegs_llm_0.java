
import java.io.*;
import java.lang.*;
import java.util.*;

class RemoveNegs {

	public static int[] removeNegs(int[] array) {
		int arr = 0;
		for (int i : array) {
			if (i >= 0) {
				arr++;
			}
		}

		int[] remove = new int[arr];
		int index = 0;
		for (int i : array) {
			if (i >= 0) {
				remove[index++] = i;
			}
		}

		return remove;
	}
}
