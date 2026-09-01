
import java.io.*;
import java.lang.*;
import java.util.*;

class RemoveNegs {

	public static int[] removeNegs(int[] numArray) {
		int count = 0;
		for (int num : numArray) {
			if (num >= 0) {
				count++;
			}
		}

		int[] result = new int[count];
		int index = 0;
		for (int num : numArray) {
			if (num >= 0) {
				result[index] = num;
				index++;
			}
		}

		return result;
	}
}
