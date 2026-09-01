
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveEven {

	public static int[] removeEven(int[] array) {
		int Array = 0;

		for (int c : array) {
			if (c % 2 != 0) {
				Array++;
			}
		}

		int[] remove = new int[Array];
		int index = 0;

		for (int c : array) {
			if (c % 2 != 0) {
				remove[index++] = c;
			}
		}

		return remove;
	}
}
