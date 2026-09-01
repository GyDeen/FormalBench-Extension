
import java.io.*;
import java.lang.*;
import java.util.*;

class RemoveNegs {

	public static int[] removeNegs(int[] numArray) {
		int count = 0;
		for (int num : numArray) {
			if (0 <= num) {
				count++;
			}
		}

		int[] result = new int[count];
		int index = 0;
		for (int num : numArray) {
			if (0 <= num) {
				result[index++] = num;
			}
		}

		return result;
	}
}
