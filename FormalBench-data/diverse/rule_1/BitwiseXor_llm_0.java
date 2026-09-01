
import java.io.*;
import java.lang.*;
import java.util.*;

class BitwiseXor {

	public static int[] bitwiseXor(int[] b, int[] test) {
		int arr = b.length;
		int[] temp = new int[arr];
		for (int index = 0; index < arr; index++) {
			temp[index] = b[index]
					^ test[index];
		}
		return temp;
	}
}
