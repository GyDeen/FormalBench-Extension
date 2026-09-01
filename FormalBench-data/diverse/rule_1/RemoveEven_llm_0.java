
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveEven {

	public static int[] removeEven(int[] array) {
		int Array = 0;

		for (int i : array) {
			if (i % 2 != 0) {
				Array++;
			}
		}

		int[] remove = new int[Array];
		int index = 0;

		for (int i : array) {
			if (i % 2 != 0) {
				remove[index++] = i;
			}
		}

		return remove;
	}
}
